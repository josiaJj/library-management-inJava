package school.hei.Repository;

import school.hei.Model.Book;
import school.hei.Model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations<Book> {


    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();

        String findAllbook_request = "SELECT book.id, book_name, page_numbers, release_date, topic_name, author_name " +
                "FROM book \n" +
                "LEFT JOIN topic ON book.topic_id = topic.id \n" +
                "LEFT JOIN author ON book.author_id = author.id";
        try (
                Connection connection = DB_Connection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(findAllbook_request)
        ) {
            while (resultSet.next()) {
                String topicName = resultSet.getString("topic_name");
                Topic topic = new Topic(topicName);

                Book book = new Book();

                book.setId(resultSet.getLong("id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setPageNumbers(resultSet.getInt("page_numbers"));
                book.setTopic(topic);
                book.setReleaseDate(resultSet.getDate("release_date"));

                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        String insertBookQuery = "INSERT INTO book (book_name, page_numbers, release_date, topic_id, author_id)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS)) {

            for (Book book : toSave) {
                preparedStatement.setString(1, book.getBookName());
                preparedStatement.setInt(2, book.getPageNumbers());
                preparedStatement.setDate(3, new java.sql.Date(book.getReleaseDate().getTime()));
                preparedStatement.setLong(4, book.getTopic().getId());
                preparedStatement.setLong(5, book.getAuthor().getId());

                preparedStatement.addBatch();
            }

            int[] affectedRows = preparedStatement.executeBatch();
            List<Book> savedBooks = new ArrayList<>();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                int i = 0;
                while (generatedKeys.next()) {
                    Book savedBook = new Book();
                    savedBook.setId(generatedKeys.getLong("id"));
                    savedBook.setBookName(generatedKeys.getString("book_name"));
                    savedBook.setPageNumbers(generatedKeys.getInt("page_numbers"));
                    savedBook.setReleaseDate(generatedKeys.getDate("release_date"));
                    savedBooks.add(savedBook);
                    i++;
                }
            }

            return savedBooks;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving books", e);
        }
    }

    public Book save(Book toSave) {
        String insertBookQuery = "INSERT INTO book (book_name, page_numbers, release_date, topic_id, author_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, toSave.getBookName());
            preparedStatement.setInt(2, toSave.getPageNumbers());
            preparedStatement.setDate(3, new java.sql.Date(toSave.getReleaseDate().getTime()));
            preparedStatement.setLong(4, toSave.getTopic().getId());
            preparedStatement.setLong(5, toSave.getAuthor().getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating book failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    toSave.setId(generatedKeys.getLong(1)); // Assuming the ID is the first column
                } else {
                    throw new SQLException("Creating book failed, no ID obtained.");
                }
            }

            return toSave;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving book", e);
        }
    }

    @Override
    public Book delete(Book toDelete) {
        String deleteBookQuery = "DELETE FROM book WHERE id = ?";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery)) {

            preparedStatement.setLong(1, toDelete.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting book failed, no rows affected.");
            }

            return toDelete;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }

    }
}
