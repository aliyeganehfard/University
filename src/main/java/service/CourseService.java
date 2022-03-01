package service;

import model.entity.Course;
import model.repository.CourseRepository;
import utils.DEPARTMENT;

import java.util.Arrays;
import java.util.List;

public class CourseService implements ServiceInterface<Course> {
    private CourseRepository courseRepository = new CourseRepository();

    @Override
    public void add(Course course) {
        courseRepository.add(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.update(course);
    }

    @Override
    public void delete(String id) {
        courseRepository.delete(id);
    }

    @Override
    public void findAll() {
        List<Course> list = courseRepository.findAll();
        list.forEach(System.out::println);
    }
    public Course findByCode(String code){
        List<Course> list = courseRepository.findAll();
        return list.stream()
                .filter(l->l.getCourseCode().equals(code))
                .findFirst()
                .get();

    }
    public void showDepartmentCourse(DEPARTMENT department) {
        List<Course> list = courseRepository.findAll();
        list.stream()
                .filter(l -> l.getDepartment().equals(department))
                .forEach(System.out::println);
    }

    public void showStudentCourses(String courseID) {
        List<Course> list = courseRepository.findAll();
        list.stream()
                .filter(l -> l.getCourseCode().equals(courseID))
                .forEach(System.out::println);
    }

    public void showStudentCourses(String[] courseID) {
        for (String s : courseID) {
            showStudentCourses(s);
        }
    }

    public void showProfessorCourse(String proNationalCode) {
        List<Course> list = courseRepository.findAll();
        list.stream()
                .filter(l -> l.getProfessorNationalCode().equals(proNationalCode))
                .forEach(System.out::println);
    }

    public int getProfessorUnit(String proID, String[] courses) {
        int sum = 0;
        List<Course> list = courseRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProfessorNationalCode().equals(proID)) {
                for (int j = 0; j < courses.length; j++) {
                    if (courses[j] != null) {
                        if (list.get(i).getCourseCode().equals(courses[j])) {
                            sum += list.get(i).getUnit();
                        }
                    }
                }
            }
        }
        return sum;
    }

    public Integer getUnit(String[] courseID) {
        List<String> courses = Arrays.asList(courseID);
        List<Course> list = courseRepository.findAll();
        return list.stream()
                .filter(l -> courses.contains(l.getCourseCode()))
                .mapToInt(Course::getUnit).sum();

    }
}
