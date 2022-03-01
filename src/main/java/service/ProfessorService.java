package service;

import model.entity.Professor;
import model.repository.ProfessorRepository;
import utils.STATUS;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProfessorService implements ServiceInterface<Professor> {
    ProfessorRepository professorRepository = new ProfessorRepository();


    @Override
    public void add(Professor professor) {
        professorRepository.add(professor);
    }

    @Override
    public void update(Professor professor) {
        professorRepository.update(professor);
    }

    @Override
    public void delete(String id) {
        professorRepository.delete(id);
    }

    @Override
    public void findAll() {
        List<Professor> professorList = professorRepository.findAll();
        System.out.println(professorList);
//        professorList.stream()
//                .forEach(System.out::println);
    }

    public boolean loginPro(String userName , String password){
        List<Professor> professorList = professorRepository.findAll();
        return professorList.stream()
                .anyMatch(professor -> professor.getProfessorCode().equals(userName) &&
                        professor.getNationalCode().equals(password));
    }

    //      public void showProfessorProfile(String proId)
    public void showProfessorProfile(String code) {
        List<Professor> professorList = professorRepository.findAll();
        professorList.stream()
                .filter(professor -> professor.getProfessorCode().equals(code))
                .forEach(System.out::println);
    }

    public Professor getProfessor(String code) {
        List<Professor> professorList = professorRepository.findAll();
        return professorList.stream()
                .filter(professor -> professor.getProfessorCode().equals(code))
                .findFirst()
                .get();
    }

    public double getProfessorSalary(String code, int unit) {
        double totalSalary = 0;
        List<Professor> professorList = professorRepository.findAll();
        for (Professor p : professorList) {
            if (p.getProfessorCode().equals(code)) {
                if (p.getStatus().equals("SCIENCE_COMMITTEE"))
                    totalSalary = 5000 + unit * 1000;
                else
                    totalSalary = unit * 1000;
            }
        }
        return totalSalary;
    }

    public String getNationalCode(String code) {
        List<Professor> professorList = professorRepository.findAll();
        for (Professor pro : professorList) {
            if (pro.getProfessorCode().equals(code)){
                return pro.getNationalCode();
            }
        }
        return null;
//        return professorList.stream()
//                .filter(professor -> professor.getProfessorCode().equals(code))
//                .map(Professor::getNationalCode).toString();
    }

    public boolean login(String code, String nationalCode) {
        List<Professor> professorList = professorRepository.findAll();
        Predicate<Professor> login = professor -> professor.getProfessorCode().equals(code) &&
                professor.getNationalCode().equals(nationalCode);
        return professorList.stream().anyMatch(login);
    }
}
