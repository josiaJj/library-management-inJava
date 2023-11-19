package school.hei.Repository;

import school.hei.Model.Sex;
import school.hei.Model.Subscribers;

import java.sql.*;
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

    public List<Subscribers> saveAll(List<Subscribers> toSave) {
        String insertSubscriberQuery = "INSERT INTO subscribers (username, password, sex) VALUES (?, ?, ?)";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSubscriberQuery, Statement.RETURN_GENERATED_KEYS)) {

            List<Subscribers> savedSubscribers = new ArrayList<>();

            for (Subscribers subscriber : toSave) {
                preparedStatement.setString(1, subscriber.getUsername());
                preparedStatement.setString(2, subscriber.getPassword());
                preparedStatement.setString(3, subscriber.getSex().toString());

                preparedStatement.addBatch();
            }

            int[] affectedRows = preparedStatement.executeBatch();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                int i = 0;
                while (generatedKeys.next()) {
                    Subscribers savedSubscriber = new Subscribers();
                    savedSubscriber.setId(generatedKeys.getLong(1));
                    savedSubscribers.add(savedSubscriber);
                    i++;
                }
            }

            if (savedSubscribers.size() != toSave.size()) {
                throw new SQLException("Not all subscribers were saved successfully.");
            }

            return savedSubscribers;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving subscribers", e);
        }
    }

    public Subscribers save(Subscribers toSave) {
        String insertSubscriberQuery = "INSERT INTO subscribers (username, password, sex) VALUES (?, ?, ?)";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSubscriberQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, toSave.getUsername());
            preparedStatement.setString(2, toSave.getPassword());
            preparedStatement.setString(3, toSave.getSex().toString());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating subscriber failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    toSave.setId(generatedKeys.getLong(1)); // Assuming the ID is the first column
                } else {
                    throw new SQLException("Creating subscriber failed, no ID obtained.");
                }
            }

            return toSave;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving subscriber", e);
        }
    }

    public Subscribers delete(Subscribers toDelete) {
        String deleteSubscriberQuery = "DELETE FROM subscribers WHERE id = ?";

        try (Connection connection = DB_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSubscriberQuery)) {

            preparedStatement.setLong(1, toDelete.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting subscriber failed, no rows affected.");
            }

            return toDelete;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting subscriber", e);
        }
    }
}
