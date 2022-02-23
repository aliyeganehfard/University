package model.entity;


import utils.RandomNumber;
import utils.STATUS;

public class Professor extends Person{
    private String professorCode;
    private String status ;

    public Professor(String firstName, String lastName, String nationalCode,
                     String birthDate,  String status) {

        super(firstName, lastName, nationalCode, birthDate);
        this.status = status;
        this.professorCode = String.valueOf(RandomNumber.getRandomNumber());
    }

    public Professor(String firstName, String lastName, String nationalCode, String birthDate, String professorCode, String status) {
        super(firstName, lastName, nationalCode, birthDate);
        this.professorCode = professorCode;
        this.status =status;
    }

    public String getStatus() {
        return status;
    }

    public String getProfessorCode() {
        return professorCode;
    }

    @Override
    public String toString() {
        return  super.toString() +
                " , professorCode=" + professorCode +
                ", status=" + status +
                '}';
    }

}
