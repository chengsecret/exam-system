package work.ctstudy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.ctstudy.entity.Answer;

@Repository
public interface AnswerMapper {
    public boolean addAnswer(Answer answer);

    public boolean updateAnswer(@Param("aid") int aid,@Param("answer") Answer answer);

    public Answer selectAnswer(String gid, String qid);
}
