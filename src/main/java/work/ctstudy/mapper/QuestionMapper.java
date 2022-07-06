package work.ctstudy.mapper;

import org.springframework.stereotype.Repository;
import work.ctstudy.entity.Question;

@Repository
public interface QuestionMapper {
    public Question selectQuestionById(String qid);
}
