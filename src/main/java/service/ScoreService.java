package service;

import model.entity.Score;
import model.repository.ScoreRepository;

import java.util.List;

public class ScoreService implements ServiceInterface<Score> {
    private ScoreRepository scoreRepository = new ScoreRepository();

    @Override
    public void add(Score score) {
        scoreRepository.add(score);
    }

    @Override
    public void update(Score score) {
        scoreRepository.update(score);
    }

    @Override
    public void delete(String id) {
        scoreRepository.delete(id);
    }

    @Override
    public void findAll() {
        List<Score> scoreList = scoreRepository.findAll();
        scoreList.forEach(System.out::println);
    }

    public void showStudentScores(String nationalCode) {
        List<Score> scoreList = scoreRepository.findAll();
//        for (Score s :scoreList) {
//            if ( s.getStudentNationalCode().equals(nationalCode)){
//                System.out.println(s);
//            }
//        }
        try {
            scoreList.stream()
                    .filter(score -> score.getStudentNationalCode().equals(nationalCode))
                    .forEach(System.out::println);
        } catch (Exception e) {

        }

    }

    public double getGradPointAverage(String nationalCode) {
        List<Score> scoreList = scoreRepository.findAll();
        return scoreList.stream()
                .filter(score -> score.getStudentNationalCode().equals(nationalCode))
                .mapToDouble(Score::getScore).average().orElse(0);
    }

}