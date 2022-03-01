package model.repository;

import model.entity.Professor;
import model.entity.Student;
import model.entity.StudentCourse;
import model.util.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseRepository implements Repository<StudentCourse> {
    private String query ;


    @Override
    public void add(StudentCourse studentCourse) {
        try {
            query ="insert into University_Student_course(student_national_code, course_code, term) VALUES (?,?,?)";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,studentCourse.getStudentNationalCode());
            preparedStatement.setString(2,studentCourse.getCourseCode());
            preparedStatement.setString(3,studentCourse.getTerm());
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
    public void update(StudentCourse studentCourse) {

    }

    @Override
    public List<StudentCourse> findAll() {
        try {
            List<StudentCourse> adminList = new ArrayList<>();
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
    public StudentCourse getResultSet(ResultSet resultSet) {
        try {
            return new StudentCourse(
                    resultSet.getString("student_national_code"),
                    resultSet.getString("course_code"),
                    resultSet.getString("term")
            );
        } catch (SQLException e) {
        }
        return null;
    }
}
