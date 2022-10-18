package co.edu.usa.ciclo3.service;

import co.edu.usa.ciclo3.entity.Score;
import co.edu.usa.ciclo3.repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional(readOnly = true)
    public List<Score> getList() {
        return scoreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Score getById(Long id) {
        return scoreRepository.findById(id).get();
    }

    @Transactional
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Transactional
    public Score update(Score score) {
        if (score.getId() != null) {
            Optional<Score> scoreId = scoreRepository.findById(score.getId());

            if (!scoreId.isEmpty()) {
                Score scoreBd = scoreId.get();

                scoreBd.setMsgText(score.getMsgText());
                scoreBd.setValueScore(score.getValueScore());
                scoreBd.setReservation(score.getReservation());

                return scoreRepository.save(scoreBd);

            } else {

                return score;
            }
        }
        return score;
    }

    @Transactional
    public void delete(Long id) {
        scoreRepository.deleteById(id);
    }

}
