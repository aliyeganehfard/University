package service;

import model.entity.Professor;
import utils.STATUS;

public class ProfessorList {
    private Professor[] list;
    private int emptyHomeIndex;

    public ProfessorList() {
        list = new Professor[1000];
        emptyHomeIndex = 0;
    }

    public void add(Professor value) {
        list[emptyHomeIndex] = value;
        emptyHomeIndex++;
    }

    public void add(String firstName, String lastName, String nationalCode,
                    String birthdate, String status) {
        add(new Professor(firstName, lastName, nationalCode, birthdate, status));
    }

    public Professor get(int index) {
        return list[index];
    }

    public Boolean isEmpty() {
        return emptyHomeIndex == 0;
    }

    public int size() {
        return emptyHomeIndex;
    }

    public void add(int index, Professor value) {
        // Check: Index invalid
        for (int i = emptyHomeIndex; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        emptyHomeIndex++;
    }

    // add array to end of current array
    public void addAll(Professor[] values) {
        for (Professor v : values) {
            add(v);
        }
    }

    //    remove number with id
    public void delete(String proID) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorCode().equals(proID)) {
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
    public boolean contains(Professor number) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] == number) {
                return true;
            }
        }
        return false;
    }

    //    edit professor profile
    public void edit(String proID, String proFirstName, String proLastName, String proNationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorCode().equals(proID)) {
                list[i].setFirstName(proFirstName);
                list[i].setLastName(proLastName);
                list[i].setNationalCode(proNationalCode);
            }
        }
    }

    //    show professor list
    public void showList() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            System.out.print(list[i]);
            System.out.print(", \n");
        }
    }

    //    show specific professor profile
    public void showProfessorProfile(String proId) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorCode().equals(proId)) {
                System.out.println(list[i]);
            }
        }
    }

    //    calculate professor salary
    public double getProfessorSalary(String professorID, int unit) {
        double totalSalary = 0;
            for (int i = 0; i < emptyHomeIndex; i++) {
                if (list[i].getProfessorCode().equals(professorID)) {
                    if (list[i].getStatus().equals(STATUS.SCIENCE_COMMITTEE)) {
                        totalSalary = 5000 + unit * 1000;
                    } else {
                        totalSalary = unit * 1000;
                    }
                }
        }
        return totalSalary;
    }

    //    professor login
    public boolean login(String proID, String nationalCode) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorCode().equals(proID) &&
                    list[i].getNationalCode().equals(nationalCode)) {
                return true;
            }
        }
        return false;
    }

    //    get professor national code
    public String getNationalCode(String proID) {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i].getProfessorCode().equals(proID)) {
                return list[i].getNationalCode();
            }
        }
        return null;
    }
}
