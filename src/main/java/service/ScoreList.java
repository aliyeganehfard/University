package service;

import model.entity.Score;

public class ScoreList {
    private Score[] list;
    private int emptyHomeIndex;

    public ScoreList() {
        list = new Score[1000];
        emptyHomeIndex = 0;
    }

    public void add(Score value) {
        list[emptyHomeIndex] = value;
        emptyHomeIndex++;
    }
    public void add(String courseCode , String studentNationalCode , String professorCode , String score){
        add(new Score(courseCode,studentNationalCode,professorCode,score));
        System.out.println("set score was successful ");
    }

    public Score get(int index) {
        return list[index];
    }

    public Boolean isEmpty() {
        return emptyHomeIndex == 0;
    }

    public int size() {
        return emptyHomeIndex;
    }

    public void add(int index, Score value) {
        // Check: Index invalid
        for (int i = emptyHomeIndex; i > index ; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        emptyHomeIndex++;
    }
// add array to end of current array
    public void addAll(Score[] values) {
        for (Score v: values) {
            add(v);
        }
    }
//    remove number with get index
    public void delete(int index){
        list[index]=null;
        resetList();
        resetEmptyHomeIndex();
    }
//    restore list after remove something
    private void resetList(){
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] == null){
                list[i]= list[i +1];
                list[i+1]=null;
            }
        }
    }
//    restore empty home index after restore list
    private void resetEmptyHomeIndex(){
        int temp = emptyHomeIndex;
        emptyHomeIndex = 0;
        for (int i = 0; i < temp; i++) {
            if (list[i] != null){
                emptyHomeIndex++;
            }
        }
    }
//  clear the array
    public void clear(){
        for (int i = 0; i < emptyHomeIndex; i++) {
            list[i]=null;
        }
        System.out.println("array cleared");
        resetList();
        resetEmptyHomeIndex();
    }
//    check if contains number
    public boolean contains(Score number){
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] == number){
                return true;
            }
        }
        return false;
    }
//    show all scores
    public void showList() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            System.out.print(list[i]);
            System.out.print(", \n");
        }
    }
    public void showList(String id) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorCode().equals(id)) {
                System.out.print(list[i]);
                System.out.print(", \n");
            }
        }
    }
//    show student scores
    public void showStudentScores(String studentID){
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentNationalCode().equals(studentID)){
                if (list[i].getScore()!=-1) {
                    System.out.println(list[i]);
                }
            }
        }
    }
//    get grad point average
    public double getGradPointAverage(String studentId){
        int counter = 0;
        double sum = 0 ;
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getStudentNationalCode().equals(studentId)){
                if (list[i].getScore() != -1){
                sum +=list[i].getScore();
                counter++;
                list[i].setScore(-1);
                }
            }
        }
        return sum / counter;
    }

}
