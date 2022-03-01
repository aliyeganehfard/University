package service;

import model.entity.Student;
import model.repository.StudentRepository;
import utils.DEPARTMENT;

import java.util.List;

public class StudentService implements ServiceInterface<Student> {
    private StudentRepository studentRepository = new StudentRepository();

    @Override
    public void add(Student student) {
        studentRepository.add(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void delete(String id) {
        studentRepository.delete(id);
    }

    @Override
    public void findAll() {
        List<Student> studentList = studentRepository.findAll();
        studentList.forEach(System.out::println);
    }

    public StudentList studentFindAll(){
        return studentRepository.studentList();
    }
    public boolean loginStu(String stuID, String nationalCode){
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .anyMatch(student -> student.getStudentCode().equals(stuID) &&
                        student.getNationalCode().equals(nationalCode));
    }


    public void showStudentProfile(String stuId){
        List<Student> studentList = studentRepository.findAll();
        studentList.stream()
                .filter(student -> student.getStudentCode().equals(stuId))
                .forEach(System.out::println);
    }
//public String[] getStudentCoursesID(String studentCode , String term)
//public DEPARTMENT getDepartment(String stuID)
//public void addTerm(String stuID, String term, String[] courseID, int unit)
//public String[] getTerm(String term) {
//public void setGrade(String stuID, double grade)
//public void getGrade(String stuID)
//public String getNationalCode(String stuId)
}
