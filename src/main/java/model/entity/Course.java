package model.entity;

import utils.DEPARTMENT;

public class Course {
    private String courseCode;
    private String courseName;
    private int unit ;
    private String professorNationalCode;
    private DEPARTMENT department;
    private String term ;
    public Course(){}
    public Course(String courseCode,String department, String courseName, String unit, String professorNationalCode ) {
        this.courseCode = courseCode;
        this.department = DEPARTMENT.valueOf(department.toUpperCase()) ;
        this.courseName = courseName;
        this.unit = Integer.valueOf(unit);
        this.professorNationalCode = professorNationalCode;
//        this.term = term;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getUnit() {
        return unit;
    }

    public String getProfessorNationalCode() {
        return professorNationalCode;
    }

    public String getTerm() {
        return term;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setUnit(String unit) {
        this.unit = Integer.valueOf(unit);
    }

    public void setProfessorNationalCode(String professorNationalCode) {
        this.professorNationalCode = professorNationalCode;
    }

    public void setDepartment(DEPARTMENT department) {
        this.department = department;
    }

    public DEPARTMENT getDepartment() {
        return department;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return "Course{" +
                "  courseCode=" + courseCode +
                "  department=" + department +
                ", CourseName='" + courseName + '\'' +
                ", unit=" + unit +
                ", professorCode=" + professorNationalCode +
                '}';
    }

}
