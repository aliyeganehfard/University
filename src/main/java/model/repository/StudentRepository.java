package model.repository;

import model.entity.Professor;
import model.entity.Student;
import model.entity.StudentCourse;
import model.entity.StudentTerm;
import model.util.PostgresConnection;
import service.StudentList;
import utils.DEPARTMENT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository implements Repository<Student> {
    private StudentCourseRepository studentCourseRepository = new StudentCourseRepository();
    private String query;

    @Override
    public void add(Student student) {
        try {
            query = "insert into University_Student(student_code, first_name, last_name, national_code, birth_date, department, grade)\n" +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, student.getStudentCode());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getNationalCode());
            preparedStatement.setDate(5, student.getBirthDate());
            preparedStatement.setString(6, student.getDepartment().name());
            preparedStatement.setDouble(7, 0.0);
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
            query = "delete from University_Student where student_code=?";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete operation was failed!");
        }

    }

    @Override
    public void update(Student student) {
        try {
            query = "update University_Student\n" +
                    "    set first_name=? , last_name=? , national_code=? , grade=?\n" +
                    "where student_code=?";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getNationalCode());
            preparedStatement.setDouble(4, student.getTerm().getGrade());
            preparedStatement.setString(5, student.getStudentCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }

    }

    @Override
    public List<Student> findAll() {
        try {
            List<Student> arrayList = new ArrayList<>();
            query = "select * from University_Student_course ";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                arrayList.add(
                        getResultSet(resultSet)
                );
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    public StudentList studentList() {
        try {
            StudentList students = new StudentList();
            query = "select * from University_Student_course ";
            PreparedStatement preparedStatement = PostgresConnection.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<StudentCourse> studentCourses = studentCourseRepository.findAll();
            while (resultSet.next()) {
                Student student = getResultSet(resultSet);
                List<StudentCourse> a = studentCourses.stream()
                        .filter(stu -> stu.getStudentNationalCode().equals(student.getNationalCode()))
                        .collect(Collectors.toList());
                for (int i = 0; i < a.size(); i++) {
                    String term = a.get(i).getTerm();

                        List<String> crs = a.stream()
                                .filter(t -> t.getTerm().equals(term))
                                .map(StudentCourse::getCourseCode)
                                .collect(Collectors.toList());

                        String[] arrayCourseCode = new String[crs.size()];
                        crs.toArray(arrayCourseCode);
                        student.setTerm(new StudentTerm(a.get(i).getTerm(), arrayCourseCode));
                    }
                    students.add(
                            student
                    );
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student getResultSet(ResultSet resultSet) {
        try {
            return new Student(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("national_code"),
                    resultSet.getString("birth_date"),
                    resultSet.getString("student_code"),
                    DEPARTMENT.valueOf(resultSet.getString("department")),
                    new StudentTerm(
                            resultSet.getDouble("grade")
                    )
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no");
        return null;

    }
}
