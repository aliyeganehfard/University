package model.repository;

import model.entity.Professor;
import model.util.PostgresConnection;
import utils.STATUS;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository implements Repository<Professor>{
    private String query;
    @Override
    public void add(Professor professor) {
        try {
            query ="insert into University_Professor(first_name, last_name, national_code, birth_date, professor_code, status)\n" +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,professor.getFirstName());
            preparedStatement.setString(2,professor.getLastName());
            preparedStatement.setString(3,professor.getNationalCode());
            preparedStatement.setDate(4,professor.getBirthDate());
            preparedStatement.setString(5,professor.getProfessorCode());
            preparedStatement.setString(6,professor.getStatus().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("save operation was failed!");
        }
    }

    @Override
    public void delete(Integer value) {
        try {
            query="delete from University_Professor where professor_code=?;";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1,value);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public void update(Professor professor) {
        try {
            query="update University_Professor\n" +
                    "set first_name=? , last_name=?,national_code=?\n" +
                    "where professor_code= ?";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,professor.getFirstName());
            preparedStatement.setString(2,professor.getLastName());
            preparedStatement.setString(3,professor.getNationalCode());
            preparedStatement.setString(4,professor.getProfessorCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<Professor> findAll() {
        try {
            List<Professor> adminList = new ArrayList<>();
            query = "select * from University_Professor";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                adminList.add(
                       getResultSet(resultSet)
                );
            }
            return adminList;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Professor getResultSet(ResultSet resultSet) {

        try {
            return new Professor(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("national_code"),
                    resultSet.getDate("birth_date").toString(),
                    resultSet.getString("professor_code"),
                    resultSet.getString("status")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no");
        return null;
    }


}
