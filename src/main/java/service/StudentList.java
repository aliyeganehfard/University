package service;

import model.entity.Student;
import model.entity.StudentTerm;
import utils.DEPARTMENT;

public class StudentList {
    private Student[] list;
    private int emptyHomeIndex;

    public StudentList() {
        list = new Student[1000];
        emptyHomeIndex = 0;
    }

    public void add(Student value) {
        list[emptyHomeIndex] = value;
        emptyHomeIndex++;
    }

    public void add(String firstName, String lastName, String nationalCode,
                    String birthdate, String department) {
        add(new Student(firstName, lastName, nationalCode, birthdate, department));
    }

    public Student get(int index) {
        return list[index];
    }

    public Boolean isEmpty() {
        return emptyHomeIndex == 0;
    }

    public int size() {
        return emptyHomeIndex;
    }

    public void add(int index, Student value) {
        // Check: Index invalid
        for (int i = emptyHomeIndex; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        emptyHomeIndex++;
    }

    // add array to end of current array
    public void addAll(Student[] values) {
        for (Student v : values) {
            add(v);
        }
    }

    //    remove number with id
    public void delete(String stuID) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentCode().equals(stuID)) {
                list[i] = null;
                resetList();
                resetEmptyHomeIndex();
            }
        }
    }

    //    restore list after remove something
    private void resetList() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] == null) {
                list[i] = list[i + 1];
                list[i + 1] = null;
            }
        }
    }

    //    restore empty home index after restore list
    private void resetEmptyHomeIndex() {
        int temp = emptyHomeIndex;
        emptyHomeIndex = 0;
        for (int i = 0; i < temp; i++) {
            if (list[i] != null) {
                emptyHomeIndex++;
            }
        }
    }

    //  clear the array
    public void clear() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            list[i] = null;
        }
        System.out.println("array cleared");
        resetList();
        resetEmptyHomeIndex();
    }

    //    check if contains number
    public boolean contains(Student number) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] == number) {
                return true;
            }
        }
        return false;
    }

    //    edit student profile
    public void edit(String studentId, String stuFirstName, String stuLastName, String stuNationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentCode().equals(studentId)) {
                list[i].setFirstName(stuFirstName);
                list[i].setLastName(stuLastName);
                list[i].setNationalCode(stuNationalCode);
            }
        }
    }

    //    show student list
    public void showList() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            System.out.print(list[i]);
            System.out.print(", \n");
        }
    }

    //    show specific student profile
    public void showStudentProfile(String stuId) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentCode().equals(stuId)) {
                System.out.println(list[i]);
            }
        }
    }

    //    return array of student(x) courses
    public String[] getStudentCoursesID(String studentCode , String term) {
        try {
            for (int i = 0; i < emptyHomeIndex; i++) {
                if (list[i].getStudentCode().equals(studentCode) &&
                list[i].getTerm().getTerm().equals(term)) {
                    return list[i].getTerm().getCoursesID();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    //    student login
    public boolean login(String stuID, String nationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentCode().equals(stuID) &&
                    list[i].getNationalCode().equals(nationalCode)) {
                return true;
            }
        }
        return false;
    }

    //    get student department
    public DEPARTMENT getDepartment(String stuID) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentCode().equals(stuID)) {
                return list[i].getDepartment();
            }
        }
        return null;
    }
//    add term

    public void addTerm(String stuID, String term, String[] courseID, int unit) {

        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentCode().equals(stuID)) {

                if (isPassed(stuID, courseID)) {
                    if (isNotDuplicate(courseID)) {
                        if (isGrade(stuID)) {
                            System.out.println("you can select 24 unit");
                            if (unit <= 24) {
                                list[i].getTerm().setGrade(0);
                                list[i].setTerm(new StudentTerm(term, courseID));
                                System.out.println("success unit selection");

                            } else {
                                System.out.println("please try again!");
                            }
                        } else {
                            System.out.println("you can select 20 unit");
                            if (unit <= 18) {
                                list[i].setTerm(new StudentTerm(term, courseID));
                                System.out.println("success unit selection");
                            } else {
                                System.out.println("please try again!");
                            }
                        }

                    } else {
                        System.out.println("some course id is Duplicate");
                    }
                } else {
                    System.out.println("you get some course before of that . please try again!");
                }
            }
        }

    }

    //    check grade more than 18 or not
    private boolean isGrade(String stuID) {
        try {


            for (int i = 0; i < emptyHomeIndex; i++) {
                if (list[i].getStudentCode().equals(stuID)) {
                    if (list[i].getTerm().getTerm().length() == 0) {
//                        System.out.println("you can select 20 unit");
                        return true;
                    } else {
                        if (list[i].getTerm().getGrade() >= 18) {
//                            System.out.println("you can select 24 unit");
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    //    check if this course selected before of not
    private boolean isPassed(String stuID, String[] courseID) {
        boolean state = true;
        try {
            for (int i = 0; i < emptyHomeIndex; i++) {
                if (list[i].getStudentCode().equals(stuID)) {
                    for (int j = 0; j < list[i].getTerm().getCoursesID().length; j++) {
                        for (int k = 0; k < courseID.length; k++) {
                            if (list[i].getTerm().getCoursesID()[j].equals(courseID[k])) {
                                state = false;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
        }
        return state;
    }

    //    check course id not duplicate
    private boolean isNotDuplicate(String[] courseID) {
        try {

            int record = 0;
            for (int i = 0; i < courseID.length; i++) {
                for (int j = 0; j < courseID.length; j++) {
                    if (courseID[i].equals(courseID[j])) {
                        record++;
                        if (record > 1) {
                            return false;
                        }
                    }
                }
                record = 0;
            }
        } catch (Exception e) {
        }
        return true;
    }

    // get student term courses

    public String[] getTerm(String term) {
        String[] coursesID = new String[100];
        int index = 0;


        try {
            for (int i = 0; i < emptyHomeIndex; i++) {
                if (list[i].getTerm().getTerm().equals(term)) {
                    for (int j = 0; j < list[i].getTerm().getCoursesID().length; j++) {
                        boolean state = true;
                        for (int k = 0; k < index; k++) {
                            if (coursesID[k].equals(list[i].getTerm().getCoursesID()[j])) {
                                state = false;
                            }
                        }
                        if (state) {
                            coursesID[index++] = list[i].getTerm().getCoursesID()[j];
                            state = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        String[] courses = new String[index];
        for (int i = 0; i < index; i++) {
            courses[i] = coursesID[i];
        }

        return courses;
    }

    //        return stu id
    public String getStudentID(String nationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getNationalCode().equals(nationalCode)) {
                return list[i].getStudentCode();
            }
        }
        return "not found";
    }

    //    set student grade
    public void setGrade(String stuID, double grade) {
        try {

            for (int i = 0; i < emptyHomeIndex; i++) {
                if (list[i].getStudentCode().equals(stuID)) {
                    list[i].getTerm().setGrade(grade);
                }
            }
        } catch (Exception e) {
        }
    }

    //    get student grade
    public void getGrade(String stuID) {
        try {


            for (int i = 0; i < emptyHomeIndex; i++) {
                if (list[i].getStudentCode().equals(stuID)) {
                    System.out.println("student GradPointAverage : "+list[i].getTerm().getGrade());
                }
            }
        } catch (Exception e) {
        }
//        double grade = 0;
//        for (int i = emptyHomeIndex-1; i >= 0; i--) {
//            if (list[i].getStudentCode().equals(stuID)){
//                if (list[i].getTerm().getGrade() != 0){
//                    grade = list[i].getTerm().getGrade();
//                }
//            }
//        }
//        return grade;
    }

    //    return national code
    public String getNationalCode(String stuId) {
        String n = "";
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentCode().equals(stuId)) {
                n = list[i].getNationalCode();
            }
        }
        return n;
    }

    public void getStudent(String[] courseID) {
        try {
        for (int i = 0; i < emptyHomeIndex; i++) {
            for (int j = 0; j < courseID.length; j++) {
                if (list[i].getTerm().getCoursesID()[i].equals(courseID[j])){
                    System.out.println(list[i]);

                }
            }
        }
        }catch (Exception e){}
    }
}
