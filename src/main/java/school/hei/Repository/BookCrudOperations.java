package school.hei.Repository;

import school.hei.Model.Book;
import school.hei.Model.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations<Book> {


    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();

        String findAllbook_request = "SELECT book.id, book_name, page_numbers, release_date, topic_name, author_name FROM book \n" +
                "LEFT JOIN topic ON book.topic_id = topic.id\n" +
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
        return null;
    }

    @Override
    public Book save(Book toSave) {
        return null;
    }

    @Override
    public Book delete(Book toDelete) {
        return null;
    }
}
