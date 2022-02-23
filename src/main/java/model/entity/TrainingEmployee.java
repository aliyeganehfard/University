package model.entity;


import utils.RandomNumber;

public class TrainingEmployee extends Person{
    private String userName ;
    private String password ;
    private String id ;

    public TrainingEmployee(String firstName, String lastName, String nationalCode,
                            String birthDate, String userName, String password) {
        super(firstName, lastName, nationalCode, birthDate);
        this.userName = userName;
        this.password = password;
        this.id = String.valueOf(RandomNumber.getRandomNumber());

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
