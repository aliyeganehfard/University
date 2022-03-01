package service;

import model.entity.Professor;
import model.entity.TrainingEmployee;
import model.repository.TrainingEmployeeRepository;

import java.util.List;

public class TrainingEmployeeService implements ServiceInterface<TrainingEmployee> {
    private TrainingEmployeeRepository trainingEmployeeRepository = new TrainingEmployeeRepository();

    @Override
    public void add(TrainingEmployee trainingEmployee) {
        trainingEmployeeRepository.add(trainingEmployee);
    }

    @Override
    public void update(TrainingEmployee trainingEmployee) {
        trainingEmployeeRepository.update(trainingEmployee);
    }

    @Override
    public void delete(String id) {
        trainingEmployeeRepository.delete(id);
    }

    @Override
    public void findAll() {
        List<TrainingEmployee> trainingEmployeeList = trainingEmployeeRepository.findAll();
        trainingEmployeeList.forEach(System.out::println);
    }
    public boolean loginTraining(String userName, String password){
        List<TrainingEmployee> trainingEmployeeList = trainingEmployeeRepository.findAll();
        return trainingEmployeeList.stream()
                .anyMatch(trainingEmployee -> trainingEmployee.getUserName().equals(userName) &&
                        trainingEmployee.getPassword().equals(password));
    }
    public TrainingEmployee getTraining(String code) {
        List<TrainingEmployee> employees = trainingEmployeeRepository.findAll();
        return employees.stream()
                .filter(trainingEmployee -> trainingEmployee.getId().equals(code))
                .findFirst()
                .get();
    }
    public double showSalary() {
        return 10000;
    }
}
