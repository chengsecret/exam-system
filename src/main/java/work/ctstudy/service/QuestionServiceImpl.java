package work.ctstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ctstudy.entity.Question;
import work.ctstudy.mapper.QuestionMapper;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question selectQuestionById(String qid) {
        return questionMapper.selectQuestionById(qid);
    }
}
