package school.hei.Repository;

import school.hei.Model.Sex;
import school.hei.Model.Subscribers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudOperations implements CrudOperations<Subscribers> {
    @Override
    public List<Subscribers> findAll() {
        List<Subscribers> subscribersList = new ArrayList<>();

        String findAllSubscribers_request = "SELECT * FROM subscribers";

        try (
                Connection connection = DB_Connection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(findAllSubscribers_request)
        ) {
            while(resultSet.next()) {
                Subscribers subscribers = new Subscribers();

                subscribers.setUsername(resultSet.getString("username"));
                subscribers.setPassword(resultSet.getString("password"));
                subscribers.setSex(Sex.valueOf(resultSet.getString("sex")));

                subscribersList.add(subscribers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subscribersList;
    }

    @Override
    public List<Subscribers> saveAll(List<Subscribers> toSave) {
        return null;
    }

    @Override
    public Subscribers save(Subscribers toSave) {
        return null;
    }

    @Override
    public Subscribers delete(Subscribers toDelete) {
        return null;
    }


}
