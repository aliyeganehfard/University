package model.repository;

import model.entity.Professor;
import model.entity.TrainingEmployee;
import model.util.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingEmployeeRepository implements Repository<TrainingEmployee> {
    private String query;
    @Override
    public void add(TrainingEmployee trainingEmployee) {
        try {
            query ="insert into University_TrainingEmployee(id, user_name, password, first_name, last_name, national_code, birth_date)\n" +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, trainingEmployee.getId());
            preparedStatement.setString(2, trainingEmployee.getUserName());
            preparedStatement.setString(3, trainingEmployee.getPassword());
            preparedStatement.setString(4, trainingEmployee.getFirstName());
            preparedStatement.setString(5, trainingEmployee.getLastName());
            preparedStatement.setString(6, trainingEmployee.getNationalCode());
            preparedStatement.setDate(7, trainingEmployee.getBirthDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("save operation was failed!");
        }
    }

    @Override
    public void delete(String value) {
        try {
            query="delete from University_TrainingEmployee where id=?";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,value);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public void update(TrainingEmployee trainingEmployee) {
        try {
            query="update University_TrainingEmployee\n" +
                    "set first_name=? , last_name=? , national_code=?\n" +
                    "where id=?";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,trainingEmployee.getFirstName());
            preparedStatement.setString(2,trainingEmployee.getLastName());
            preparedStatement.setString(3,trainingEmployee.getNationalCode());
            preparedStatement.setString(4,trainingEmployee.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<TrainingEmployee> findAll() {
        try {
            List<TrainingEmployee> trainingEmployees = new ArrayList<>();
            query = "select * from University_TrainingEmployee";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                trainingEmployees.add(
                        getResultSet(resultSet)
                );
            }
            return trainingEmployees;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public TrainingEmployee getResultSet(ResultSet resultSet) {
        try {
            return new TrainingEmployee(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("national_code"),
                    resultSet.getString("birth_date"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    resultSet.getString("id")
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no");
        return null;
    }
}
