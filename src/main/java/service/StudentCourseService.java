package service;

import model.entity.StudentCourse;
import model.repository.StudentCourseRepository;

import java.util.List;

public class StudentCourseService implements ServiceInterface<StudentCourse> {
    private StudentCourseRepository studentCourseRepository= new StudentCourseRepository();
    @Override
    public void add(StudentCourse studentCourse) {
        studentCourseRepository.add(studentCourse);
    }

    @Override
    public void update(StudentCourse studentCourse) {
studentCourseRepository.update(studentCourse);
    }

    @Override
    public void delete(String id) {
studentCourseRepository.delete(id);
    }

    @Override
    public void findAll() {
        List<StudentCourse> courseList = studentCourseRepository.findAll();
        courseList.forEach(System.out::println);
    }
}
