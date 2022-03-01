package model.entity;

public class StudentCourse {
    private String studentNationalCode;
    private String courseCode ;
    private String term;

    public StudentCourse() {
    }

    public StudentCourse(String studentNationalCode, String courseCode, String term) {
        this.studentNationalCode = studentNationalCode;
        this.courseCode = courseCode;
        this.term = term;
    }

    public String getStudentNationalCode() {
        return studentNationalCode;
    }

    public void setStudentNationalCode(String studentNationalCode) {
        this.studentNationalCode = studentNationalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "studentNationalCode='" + studentNationalCode + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", term='" + term + '\'' +
                '}';
    }
}
