package model.entity;

import utils.DEPARTMENT;
import utils.RandomNumber;

public class Student extends Person {
    private String studentCode ;
    private DEPARTMENT department ;
    private StudentTerm term;

    public Student(String firstName, String lastName, String nationalCode,
                   String birthDate,
                   String department ) {
        super(firstName, lastName, nationalCode, birthDate);
        this.department = DEPARTMENT.valueOf(department.toUpperCase());
        this.studentCode = String.valueOf(RandomNumber.getRandomNumber());
    }


    public String getStudentCode() {
        return studentCode;
    }

    public StudentTerm getTerm() {
        return term;
    }


    public void setTerm(StudentTerm term) {
        this.term = term;
    }

    public DEPARTMENT getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", studentCode=" + studentCode +
                ", department=" + department +
                '}';
    }
}

