package model.repository;

import model.entity.Professor;
import model.entity.Score;
import model.util.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScoreRepository implements Repository<Score> {
    private String query;
    @Override
    public void add(Score score) {
        try {
            query ="insert into University_Score(course_code, student_national_code, professor_code, score)\n" +
                    "VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,score.getCourseCode());
            preparedStatement.setString(2,score.getStudentNationalCode());
            preparedStatement.setString(3,score.getProfessorCode());
            preparedStatement.setDouble(4,score.getScore());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("save operation was failed!");
        }

    }

    @Override
    public void delete(String value) {

    }

    @Override
    public void update(Score score) {

    }

    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public Score getResultSet(ResultSet resultSet) {
        try {
            return new Score(
                    resultSet.getString("course_code"),
                    resultSet.getString("student_national_code"),
                    resultSet.getString("professor_code"),
                    resultSet.getString("score")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no");
        return null;

    }
}
