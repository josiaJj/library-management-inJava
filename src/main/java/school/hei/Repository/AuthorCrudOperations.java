package school.hei.Repository;

import school.hei.Model.Author;
import school.hei.Model.Sex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations {
    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();

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
        return null;
    }

    @Override
    public Author save(Author toSave) {
        return null;
    }

    @Override
    public Author delete(Author toDelete) {
        return null;
    }
}
