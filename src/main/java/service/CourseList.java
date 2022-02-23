package service;

import model.entity.Course;
import utils.*;

public class CourseList {
    private Course[] list = new Course[1000];
    private int emptyHomeIndex = 0;

    public CourseList() {
    }

    public void add(String courseCode, String department, String courseName,
                    String unit, String professorNationalCode) {
        add(new Course(courseCode, department, courseName, unit, professorNationalCode));
    }

    public Course get(int index) {
        return list[index];
    }

    public Boolean isEmpty() {
        return emptyHomeIndex == 0;
    }

    public int size() {
        return emptyHomeIndex;
    }

    public void add(int index, Course value) {
        // Check: Index invalid
        for (int i = emptyHomeIndex; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        emptyHomeIndex++;
    }

    public void add(Course value) {
        list[emptyHomeIndex] = value;
        emptyHomeIndex++;
    }

    public void add(Course... value) {
        for (Course v : value) {
            add(v);
        }
    }

    // add array to end of current array
    public void addAll(Course[] values) {
        for (Course v : values) {
            add(v);
        }
    }

    //    remove course
    public void delete(String courseID) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getCourseCode().equals(courseID)) {
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
    public boolean contains(Course number) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] == number) {
                return true;
            }
        }
        return false;
    }

    //    edit course
    public void edit(String courseID, String name, String unit, String proNationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getCourseCode().equals(courseID)) {
                list[i].setCourseName(name);
                list[i].setUnit(unit);
                list[i].setProfessorNationalCode(proNationalCode);
            }
        }
    }

    //    show all courses
    public void showList() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            System.out.print(list[i] + "\n");
        }
    }
    public String[] getProfessorCourseID(String nationalCode){
        String[] courseID = new String[100];
        int index = 0;
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorNationalCode().equals(nationalCode)){
                courseID[index] = list[i].getCourseCode();
                index++;
            }
        }
        String course[] = new String[index];
        for (int i = 0; i < course.length; i++) {
            course[i]=courseID[i];
        }
        return course;
    }
    //    show specific department courses
    public void showDepartmentCourse(DEPARTMENT department) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getDepartment().equals(department)) {
                System.out.println(list[i] + "");
            }
        }
    }

    //    show specific courses
    public void showStudentCourses(String courseID ) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getCourseCode().equals(courseID)) {
                System.out.println(list[i]);
            }
        }
    }

    //  show array of specific courses
    public void showStudentCourses(String[] courseID) {
      try {


        for (int i = 0; i < courseID.length; i++) {
            if (courseID[i] != null) {
                showStudentCourses(courseID[i]);
            }
        }
      }catch (Exception e){}
    }

    //    get professor courses
//    public int getProfessorUnit(String professorID, String term) {
//        int totalUnit = 0;
//        for (int i = 0; i < emptyHomeIndex; i++) {
//            if (list[i].getProfessorNationalCode().equals(professorID)) {
//                if (list[i].getTerm().equals(term)) {
//                    totalUnit += list[i].getUnit();
//                }
//            }
//        }
//        return totalUnit;
//    }

    //    get array of course id and show some course that don't get
    public void selectUnit(DEPARTMENT department) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getDepartment().equals(department)) {
                System.out.println(list[i]);
            }
        }
    }

    //    show professor courses
    public void showProfessorCourses(String proNationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorNationalCode().equals(proNationalCode)) {
                System.out.println(list[i]);
            }
        }
    }

    //    get professor unit
    public int getProfessorUnit(String proID, String[] courses) {
        int sum = 0;
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorNationalCode().equals(proID)) {
                for (int j = 0; j < courses.length; j++) {
                    if (courses[j] != null) {
                        if (list[i].getCourseCode().equals(courses[j])) {
                            sum += list[i].getUnit();
                        }
                    }
                }
            }
        }
        return sum;
    }
    public Integer getUnit(String[] courseID){
        Integer sum = 0;
        for (int i = 0; i < emptyHomeIndex; i++) {
            for (int j = 0; j < courseID.length; j++) {
                if (list[i].getCourseCode().equals(courseID[j])){
                    sum += list[i].getUnit();
                }
            }
        }
        return sum;
    }
}
