package model.entity;

public class Score {
    private String courseCode;
    private String studentNationalCode;
    private String professorCode;
    private int score;

    public Score(String courseCode, String studentNationalCode, String professorCode,String score) {
        setCourseCode(courseCode);
        setStudentNationalCode(studentNationalCode);
        setProfessorCode(professorCode);
        setScore(Integer.valueOf(score));
    }

    public String getProfessorCode() {
        return professorCode;
    }

    public void setProfessorCode(String professorCode) {
        this.professorCode = professorCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStudentNationalCode() {
        return studentNationalCode;
    }

    public void setStudentNationalCode(String studentCode) {
        this.studentNationalCode = studentCode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score == -1){
            this.score = score;
            return;
        }
        if (score >= 0 && score <= 20)
            this.score = score;
        else{
            System.out.println("out of bond");
            this.score = 0;
        }
    }

    @Override
    public String toString() {
        return "Score{" +
                "courseCode=" + courseCode +
                ", studentCode=" + studentNationalCode +
                ", professorCode=" + professorCode +
                ", score=" + score +
                '}';
    }
}
