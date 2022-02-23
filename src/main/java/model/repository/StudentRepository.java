package model.repository;

import model.entity.Student;

import java.sql.ResultSet;
import java.util.List;

public class StudentRepository implements Repository<Student>{
    @Override
    public void add(Student student) {

    }

    @Override
    public void delete(Integer value) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student getResultSet(ResultSet resultSet) {
        return null;
    }
}
