package service;

import model.entity.TrainingEmployee;

public class TrainingEmployeeList {
    private TrainingEmployee[] list;
    private int emptyHomeIndex;

    public TrainingEmployeeList() {
        list = new TrainingEmployee[1000];
        emptyHomeIndex = 0;
    }

    public void add(TrainingEmployee value) {
        list[emptyHomeIndex] = value;
        emptyHomeIndex++;
    }

    public void add(String firstName, String lastName, String nationalCode
            , String birthDate, String userName, String password) {
        add(new TrainingEmployee(firstName, lastName, nationalCode,
                birthDate, userName, password));
    }

    public TrainingEmployee get(int index) {
        return list[index];
    }

    public Boolean isEmpty() {
        return emptyHomeIndex == 0;
    }

    public int size() {
        return emptyHomeIndex;
    }

    public void add(int index, TrainingEmployee value) {
        // Check: Index invalid
        for (int i = emptyHomeIndex; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        emptyHomeIndex++;
    }

    // add array to end of current array
    public void addAll(TrainingEmployee[] values) {
        for (TrainingEmployee v : values) {
            add(v);
        }
    }

    //    remove number with get index
    //    remove number with id
    public void delete(String treID){
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getId().equals(treID)){
                list[i]=null;
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
    public boolean contains(TrainingEmployee number) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] == number) {
                return true;
            }
        }
        return false;
    }

    //    check if user and password
    public boolean login(String userName, String password) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getUserName().equals(userName) && list[i].getPassword().equals(password)) {
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
// edit
    public void edit(String treID, String proFirstName, String proLastName, String proNationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getId().equals(treID)) {
                list[i].setFirstName(proFirstName);
                list[i].setLastName(proLastName);
                list[i].setNationalCode(proNationalCode);
            }
        }
    }
    //  show salary
    public double showSalary() {
        return 10000;
    }


}
