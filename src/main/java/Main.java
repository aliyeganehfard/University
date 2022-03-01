

import Exceptions.*;
import model.entity.Course;
import model.entity.Professor;
import model.entity.Score;
import model.entity.TrainingEmployee;
import model.repository.TrainingEmployeeRepository;
import service.*;
import utils.ExceptionHandling;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StudentService studentService = new StudentService();


//      default value
        TrainingEmployeeList trainingEmployeeList = new TrainingEmployeeList();
        trainingEmployeeList.add("mohammad", "mohammadi", "5865468445", "2000-02-02", "admin", "admin");

//        StudentList studentList = new StudentList();
        StudentList studentList = studentService.studentFindAll();
        studentList.add("ali", "yegane", "2500555555", "2002-02-02", "COMPUTER");
        studentList.add("majid", "majidi", "2500562078", "2008-03-02", "COMPUTER");

        ProfessorList professorList = new ProfessorList();
        professorList.add("hossai", "yegane", "2500000000", "2000-11-11", "SCIENCE_COMMITTEE");
        professorList.add("omid", "jafari", "2500000001", "2000-11-11", "SCIENCE_COMMITTEE");

        CourseList courseList = new CourseList();
        courseList.add("1", "COMPUTER", "java", "4", "2500000000");
        courseList.add("2", "COMPUTER", "c#", "4", "2500000000");
        courseList.add("3", "COMPUTER", "scala", "24", "2500000001");
        courseList.add("4", "COMPUTER", "c++", "4", "2500000001");


        ScoreList scoreList = new ScoreList();
        System.out.println("------------ trainingEmployeeList Information ------------");
        trainingEmployeeList.showList();
        System.out.println();
        System.out.println("------------ student Information ------------");
        studentList.showList();
        System.out.println();
//        System.out.println("------------ professor Information ------------");
//        professorList.showList();
//        System.out.println();
        Scanner scn = new Scanner(System.in);
        ProfessorService professorService = new ProfessorService();
        CourseService courseService = new CourseService();
        ScoreService scoreService = new ScoreService();
        TrainingEmployeeService trainingEmployeeService = new TrainingEmployeeService();
        boolean flag = false;
        boolean state = true;
        int permission = -1;
        String tempTerm = "";
        String id = "";
        String commendLine;
        String[] commend;
        while (state) {
            printLoginForm();
            commendLine = scn.nextLine().trim();
            commend = commendLine.split(" ");
            if (commend[0].equals("login")) {
//                if (trainingEmployeeList.login(commend[1], commend[2])) {
                if (trainingEmployeeService.loginTraining(commend[1], commend[2])) {
                    id = commend[2];
                    permission = 1;
                    flag = true;
                    printTrainingEmployeeCommend();
                } else if (studentList.login(commend[1], commend[2])) {
                    id = commend[1];
                    permission = 2;
                    flag = true;
                    printStudentCommend();
//                } else if (professorList.login(commend[1], commend[2])) {
                } else if (professorService.loginPro(commend[1], commend[2])) {
                    id = commend[1];
                    permission = 3;
                    flag = true;
                    printProfessorCommend();
                } else {
                    System.out.println("wrong userName or password!");
                }
            }

            while (flag) {
                switch (permission) {
                    case 1:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine().trim();
                        commend = commendLine.split(" ");
                        switch (commend[0]) {
                            case "registerStudent":
                                try {
                                    ExceptionHandling.isFirstName(commend[1]);
                                    ExceptionHandling.isLastName(commend[2]);
                                    ExceptionHandling.isNationalCode(commend[3]);
                                    ExceptionHandling.isDate(commend[4]);
                                    ExceptionHandling.isDepartment(commend[5]);
                                    studentList.add(commend[1], commend[2], commend[3], commend[4], commend[5]);
                                } catch (FirstNameException firstNameException) {
                                    System.out.println("incorrect firstName!");
                                } catch (LastNameException lastNameException) {
                                    System.out.println("incorrect lastName!");
                                } catch (DateException dateException) {
                                    System.out.println("incorrect date!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect national code!");
                                } catch (DepartmentException departmentException) {
                                    System.out.println("incorrect department!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "deleteStudent":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    studentList.delete(commend[1]);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "editStudent":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isFirstName(commend[2]);
                                    ExceptionHandling.isLastName(commend[3]);
                                    ExceptionHandling.isNationalCode(commend[4]);
                                    studentList.edit(commend[1], commend[2], commend[3], commend[4]);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (FirstNameException firstNameException) {
                                    System.out.println("incorrect firstName!");
                                } catch (LastNameException lastNameException) {
                                    System.out.println("incorrect lastName!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect national code!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }

                                break;
                            case "registerProfessor":
                                try {
                                    ExceptionHandling.isFirstName(commend[1]);
                                    ExceptionHandling.isLastName(commend[2]);
                                    ExceptionHandling.isNationalCode(commend[3]);
                                    ExceptionHandling.isDate(commend[4]);
                                    ExceptionHandling.isStatus(commend[5]);
                                    professorList.add(commend[1], commend[2], commend[3], commend[4], commend[5]);
                                    professorService.add(new Professor(commend[1], commend[2], commend[3], commend[4], commend[5]));
                                } catch (FirstNameException firstNameException) {
                                    System.out.println("incorrect firstName!");
                                } catch (LastNameException lastNameException) {
                                    System.out.println("incorrect lastName!");
                                } catch (DateException dateException) {
                                    System.out.println("incorrect date!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect national code!");
                                } catch (StatusException statusException) {
                                    System.out.println("incorrect department!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "deleteProfessor":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
//                                    professorList.delete(commend[1]);
                                    professorService.delete(commend[1]);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "editProfessor":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isFirstName(commend[2]);
                                    ExceptionHandling.isLastName(commend[3]);
                                    ExceptionHandling.isNationalCode(commend[4]);
//                                    editProfessor professorCode newFirstName newLastName newNationalCode
                                    Professor professor = professorService.getProfessor(commend[1]);
                                    professor.setFirstName(commend[2]);
                                    professor.setLastName(commend[3]);
                                    professor.setNationalCode(commend[4]);
//                                    professorList.edit(commend[1], commend[2], commend[3], commend[4]);
                                    professorService.update(professor);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (FirstNameException firstNameException) {
                                    firstNameException.printStackTrace();
                                    System.out.println("incorrect firstName!");
                                } catch (LastNameException lastNameException) {
                                    System.out.println("incorrect lastName!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect national code!");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "registerTrainingEmployee":
                                try {
                                    ExceptionHandling.isFirstName(commend[1]);
                                    ExceptionHandling.isLastName(commend[2]);
                                    ExceptionHandling.isNationalCode(commend[3]);
                                    ExceptionHandling.isDate(commend[4]);
//                                    trainingEmployeeList.add(commend[1], commend[2], commend[3], commend[4], commend[5], commend[6]);
                                    trainingEmployeeService.add(new TrainingEmployee(commend[1], commend[2], commend[3], commend[4], commend[5], commend[6]));
                                } catch (FirstNameException firstNameException) {
                                    System.out.println("incorrect firstName!");
                                } catch (LastNameException lastNameException) {
                                    System.out.println("incorrect lastName!");
                                } catch (DateException dateException) {
                                    System.out.println("incorrect date!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect national code!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "deleteTrainingEmployee":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
//                                    trainingEmployeeList.delete(commend[1]);
                                    trainingEmployeeService.delete(commend[1]);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "editTrainingEmployee":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isFirstName(commend[2]);
                                    ExceptionHandling.isLastName(commend[3]);
                                    ExceptionHandling.isNationalCode(commend[4]);
//                                    trainingEmployeeList.edit(commend[1], commend[2], commend[3], commend[4]);
                                    TrainingEmployee trainingEmployee = trainingEmployeeService.getTraining(commend[1]);
                                    trainingEmployee.setFirstName(commend[2]);
                                    trainingEmployee.setLastName(commend[3]);
                                    trainingEmployee.setNationalCode(commend[4]);
                                    trainingEmployeeService.update(trainingEmployee);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (FirstNameException firstNameException) {
                                    System.out.println("incorrect firstName!");
                                } catch (LastNameException lastNameException) {
                                    System.out.println("incorrect lastName!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect national code!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "addCourse":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isDepartment(commend[2]);
                                    ExceptionHandling.isWord(commend[3]);
                                    ExceptionHandling.isUnit(commend[4]);
                                    ExceptionHandling.isNationalCode(commend[5]);
//                                    courseList.add(commend[1], commend[2], commend[3], commend[4], commend[5]);
                                    courseService.add(new Course(commend[1], commend[2], commend[3], commend[4], commend[5]));
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (DepartmentException digitException) {
                                    System.out.println("incorrect department!");
                                } catch (WordException wordException) {
                                    System.out.println("incorrect courseName!");
                                } catch (UnitException unitException) {
                                    System.out.println("incorrect unit!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect NationalCode!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "deleteCourse":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
//                                    courseList.delete(commend[1]);
                                    courseService.delete(commend[1]);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "editCourse":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isWord(commend[2]);
                                    ExceptionHandling.isUnit(commend[3]);
                                    ExceptionHandling.isNationalCode(commend[4]);
//                                    courseList.edit(commend[1], commend[2], commend[3], commend[4]);
                                    Course course = courseService.findByCode(commend[1]);
                                    course.setCourseName(commend[2]);
                                    course.setUnit(commend[3]);
                                    course.setProfessorNationalCode(commend[4]);
                                    courseService.update(course);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code");
                                } catch (WordException wordException) {
                                    System.out.println("incorrect courseName!");
                                } catch (UnitException unitException) {
                                    System.out.println("incorrect unit!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect NationalCode!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "salary":
//                                System.out.println(trainingEmployeeList.showSalary());
                                System.out.println(trainingEmployeeService.showSalary());
                                break;
                            case "showStudentList":
                                studentList.showList();
                                break;
                            case "showProfessorList":
                                professorService.findAll();
//                                professorList.showList();
                                break;
                            case "showTrainingEmployeeList":
//                                trainingEmployeeList.showList();
                                trainingEmployeeService.findAll();
                                break;
                            case "showCourseList":
//                                courseList.showList();
                                courseService.findAll();
                                break;
                            case "help":
                                printTrainingEmployeeCommend();
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                state = false;
                                flag = false;
                                break;
                            default:
                                System.out.println("wrong input!");
                                break;
                        }
                        break;
                    case 2:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine().trim();
                        commend = commendLine.split(" ");
                        switch (commend[0]) {
                            case "showProfile":
                                studentList.showStudentProfile(id);
                                break;
                            case "showCoursesList":
//                                courseList.showDepartmentCourse(studentList.getDepartment(id));
                                courseService.showDepartmentCourse(studentList.getDepartment(id));
                                break;
                            case "selectUnit":
                                String[] courses = new String[commend.length - 2];
                                for (int i = 2; i < commend.length; i++) {
                                    courses[i - 2] = commend[i];
                                }
                                tempTerm = commend[1];
                                try {
//                                    Integer unit = courseList.getUnit(courses);
                                    Integer unit = courseService.getUnit(courses);
                                    studentList.addTerm(id, commend[1], courses, unit);
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "showSelectedCourses":
                                try {
//                                courseList.showStudentCourses(studentList.getStudentCoursesID(id, tempTerm));
                                    courseService.showStudentCourses(studentList.getStudentCoursesID(id, tempTerm));
                                    String n = studentList.getNationalCode(id);
                                scoreList.showStudentScores(n);
//                                    scoreService.showStudentScores(n);
                                studentList.setGrade(id, scoreList.getGradPointAverage(n));
                                    studentList.setGrade(id, scoreService.getGradPointAverage(n));

                                    studentList.getGrade(id);
                                } catch (Exception ignored) {
                                }
                                break;
                            case "help":
                                printStudentCommend();
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                state = false;
                                flag = false;
                                break;
                            default:
                                System.out.println("wrong input!");
                                break;
                        }
                        break;
                    case 3:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine().trim();
                        commend = commendLine.split(" ");
                        switch (commend[0]) {
                            case "showProfile":
//                                professorList.showProfessorProfile(id);
                                professorService.showProfessorProfile(id);
                                break;
                            case "showCourses":
//                                courseList.showProfessorCourses(professorList.getNationalCode(id));
//                                courseList.showProfessorCourses(professorService.getNationalCode(id));
                                courseService.showProfessorCourse(professorService.getNationalCode(id));
                                break;
                            case "showStudent":
                                studentList.showList();
                                break;
                            case "setScore":
//                                setScore courseCode studentNationalCode score
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isNationalCode(commend[2]);
                                    ExceptionHandling.isScore(commend[3]);
//                                    scoreList.add(commend[1], commend[2], id, commend[3]);
                                    scoreService.add(new Score(commend[1], commend[2], id, commend[3]));
                                } catch (DigitException digitException) {
                                    System.out.println("incorrect code!");
                                } catch (NationalCodeException nationalCodeException) {
                                    System.out.println("incorrect nationalCode!");
                                } catch (ScoreException scoreException) {
                                    System.out.println("incorrect score!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "showSalary":
                                String nationalCode = professorService.getNationalCode(id);
//                                String nationalCode = professorList.getNationalCode(id);
//                                System.out.println(professorList.getProfessorSalary(id, courseList.getProfessorUnit(nationalCode, studentList.getTerm(commend[1]))));
                                System.out.println(
                                        professorList.getProfessorSalary(
                                                id,
                                                courseService.getProfessorUnit(
                                                        nationalCode,
                                                        studentList.getTerm(commend[1])
                                                )
                                        )
                                );
                                break;
                            case "help":
                                printProfessorCommend();
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                state = false;
                                flag = false;
                                break;
                            default:
                                System.out.println("wrong input!");
                                break;
                        }
                        break;
                }

            }
        }
    }

    public static void printLoginForm() {
        System.out.println("login userName password \t {user name => studentCode || professorCode && password => nationalCode}");
        System.out.print("commend : ");
    }

    public static void printTrainingEmployeeCommend() {
        System.out.println("registerStudent firstName LastName nationalCode birthDate department");
        System.out.println("{ department => COMPUTER , ACCOUNTING , ELECTRONIC , ELECTRICAL }");
        System.out.println("deleteStudent studentCode");
        System.out.println("editStudent studentCode newFirstName newLastName newNationalCode");
        System.out.println("registerProfessor firstName LastName nationalCode birthDate status");
        System.out.println("{ status => SCIENCE_COMMITTEE , TUITION_FEE}");
        System.out.println("deleteProfessor professorCode");
        System.out.println("editProfessor professorCode newFirstName newLastName newNationalCode");
        System.out.println("registerTrainingEmployee firstName LastName nationalCode birthDate userName password");
        System.out.println("deleteTrainingEmployee TrainingEmployeeCode ");
        System.out.println("editTrainingEmployee TrainingEmployeeCode newFirstName newLastName newNationalCode");
        System.out.println("addCourse courseCode department courseName unit professorNationalCode");
        System.out.println("{ department => COMPUTER , ACCOUNTING , ELECTRONIC , ELECTRICAL }");
        System.out.println("deleteCourse courseCode");
        System.out.println("editCourse courseCode newCourseName newUnit newProfessorNationalCode");
        System.out.println("salary");
        System.out.println("showStudentList");
        System.out.println("showProfessorList");
        System.out.println("showTrainingEmployeeList");
        System.out.println("showCourseList");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");

    }

    public static void printStudentCommend() {
        System.out.println("showProfile");
        System.out.println("showCoursesList");
        System.out.println("selectUnit term courseCode... \t{ selectUnit 1-95 1 2 \" you can write multi course id \" }");
        System.out.println("showSelectedCourses  \t {show selected course & score & grad point average }");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }

    public static void printProfessorCommend() {
        System.out.println("showProfile");
        System.out.println("showCourses");
        System.out.println("showStudent");
        System.out.println("setScore courseCode studentNationalCode score");
        System.out.println("showSalary term");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }

}
