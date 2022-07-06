package work.ctstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.ctstudy.entity.Answer;
import work.ctstudy.entity.Grade;
import work.ctstudy.entity.Question;
import work.ctstudy.mapper.AnswerMapper;
import work.ctstudy.mapper.GradeMapper;
import work.ctstudy.mapper.QuestionMapper;
import work.ctstudy.pojo.Ans;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public int addAnswerReturnScore(String uid, String pid, Ans ans) {
        int score = 0;
        String qid = ans.getQid();
        Question question = questionMapper.selectQuestionById(qid);
        Grade grade = gradeMapper.selectGrade(uid, pid);
        int gid = grade.getGid();
        Answer answer = answerMapper.selectAnswer(Integer.toString(gid), qid);
        if (grade != null) { //添加答题情况记录
            if (answer == null) {
                //添加答题情况记录
                if (question.getType() == 0) { //如果是选择题，默认批改，返回分数

                    if(ans.getContext() != null && ans.getContext().equalsIgnoreCase(question.getKey())){
                        score=question.getScore(); //答案正确
                    }else{
                        score=0;
                    }
                    answerMapper.addAnswer(new Answer(-1,gid,Integer.parseInt(qid),ans.getContext(),1,score));
                }else {  //如果是填空简答题，默认不批改，分数返回0
                    answerMapper.addAnswer(new Answer(-1,gid,Integer.parseInt(qid),ans.getContext(),0,score));
                }

            }else{//修改答题情况记录,会覆盖原来的答案
                if (question.getType() == 0) {//如果是选择题，默认批改，返回分数
                    if(ans.getContext() != null && ans.getContext().equalsIgnoreCase(question.getKey())){
                        score=question.getScore(); //答案正确
                    }else {
                        score=0;
                    }
                    answerMapper.updateAnswer(answer.getAid(),new Answer(-1,gid,Integer.parseInt(qid),ans.getContext(),1,score));
                }else { //填空题简答题
                    answerMapper.updateAnswer(answer.getAid(),new Answer(-1,gid,Integer.parseInt(qid),ans.getContext(),0,score));
                }
            }
        }else {
            throw new  RuntimeException("没有成绩表");
        }
        return score;
    }
}
