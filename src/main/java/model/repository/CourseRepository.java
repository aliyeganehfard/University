package model.repository;

import model.entity.Course;
import model.entity.Professor;
import model.util.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements Repository<Course>{
    private String query;
    @Override
    public void add(Course course) {
        try {
            query ="insert into University_Course(course_code, course_name, unit, professor_national_code, department)\n" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,course.getCourseCode());
            preparedStatement.setString(2,course.getCourseName());
            preparedStatement.setInt(3,course.getUnit());
            preparedStatement.setString(4,course.getProfessorNationalCode());
            preparedStatement.setString(5,course.getDepartment().name());
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
            query="delete from University_Course where course_code=?";
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
    public void update(Course course) {
        try {
            query="update University_Course\n" +
                    "set course_name = ? , unit=? , professor_national_code=?\n" +
                    "where course_code =?;\n";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1,course.getCourseName());
            preparedStatement.setInt(2,course.getUnit());
            preparedStatement.setString(3,course.getProfessorNationalCode());
            preparedStatement.setString(4,course.getCourseCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public List<Course> findAll() {
        try {
            List<Course> courses = new ArrayList<>();
            query = "select * from University_Course";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                courses.add(
                        getResultSet(resultSet)
                );
            }
            return courses;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Course getResultSet(ResultSet resultSet) {
        try {
            return new Course(
                    resultSet.getString("course_code"),
                    resultSet.getString("department"),
                    resultSet.getString("course_name"),
                    resultSet.getString("unit"),
                    resultSet.getString("professor_national_code")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no");
        return null;
    }
}
