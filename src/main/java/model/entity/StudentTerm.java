package model.entity;

import java.util.Arrays;

public class StudentTerm {
   private String term ;
   private String[] coursesID = new String[1000];
   private Integer emptyHomeIndex = 0;
   private double grade ;
   public StudentTerm(){}

   public StudentTerm(double grade) {
      this.grade = grade;
   }

   public StudentTerm(String term, String[] coursesID) {
      this.term = term;
      addCourse(coursesID);
   }

   public double getGrade() {
      return grade;
   }

   public void setGrade(double grade) {
      this.grade = grade;
   }

   public void addCourse(String coursesID) {
      this.coursesID[emptyHomeIndex] = coursesID;
      emptyHomeIndex++;
   }
   public void addCourse(String[] coursesID){
      for (String digit :coursesID) {
         addCourse(digit);
      }
   }

   public String getTerm() {
      return term;
   }

   public String[] getCoursesID() {
      return coursesID;
   }

   @Override
   public String toString() {
      return "StudentTerm{" +
              "coursesID=" + Arrays.toString(coursesID) +
              '}';
   }
}
