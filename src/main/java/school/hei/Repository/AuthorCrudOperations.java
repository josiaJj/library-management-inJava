package school.hei.Repository;

import school.hei.Model.Author;
import school.hei.Model.Book;
import school.hei.Model.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author> {
    @Override
    public List<Author> findAll() {
        List<Author> authorList;
        authorList = new ArrayList<>();

        String findAllAuthor_request = "SELECT * FROM author";

        try (
                Connection connection = DB_Connection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(findAllAuthor_request)
        ) {
            while(resultSet.next()) {
                Author author = new Author();

                author.setId(resultSet.getLong("id"));
                author.setAuthorName(resultSet.getString("author_name"));
                author.setSex(Sex.valueOf(resultSet.getString("sex")));

                authorList.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorList;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        String insertAuthorQuery = "INSERT INTO author (author_name, sex) VALUES (?, ?)";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorQuery, Statement.RETURN_GENERATED_KEYS)) {

            List<Author> savedAuthors = new ArrayList<>();

            for (Author author : toSave) {
                preparedStatement.setString(1, author.getAuthorName());
                preparedStatement.setString(2, author.getSex().toString());

                preparedStatement.addBatch();
            }

            int[] affectedRows = preparedStatement.executeBatch();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                int i = 0;
                while (generatedKeys.next()) {
                    Author savedAuthor = new Author();
                    savedAuthor.setId(generatedKeys.getLong(1));
                    savedAuthors.add(savedAuthor);
                    i++;
                }
            }

            return savedAuthors;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving authors", e);
        }
    }

    @Override
    public Author save(Author toSave) {
        String insertAuthorQuery = "INSERT INTO author (author_name, sex) VALUES (?, ?)";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, toSave.getAuthorName());
            preparedStatement.setString(2, toSave.getSex().toString());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating author failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    toSave.setId(generatedKeys.getLong(1)); // Assuming the ID is the first column
                } else {
                    throw new SQLException("Creating author failed, no ID obtained.");
                }
            }

            return toSave;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving author", e);
        }
    }

    @Override
    public Author delete(Author toDelete) {
        String deleteAuthorQuery = "DELETE FROM author WHERE id = ?";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteAuthorQuery)) {

            preparedStatement.setLong(1, toDelete.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting author failed, no rows affected.");
            }

            return toDelete;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting author", e);
        }
    }


}
