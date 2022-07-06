package work.ctstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ctstudy.entity.Question;
import work.ctstudy.service.QuestionService;

@Controller
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 获取题目内容
     * @param qid
     * @return
     */
    @RequestMapping("get")
    @ResponseBody
    public String get(String qid) {
        Question question = questionService.selectQuestionById(qid);
        return question.getContent();
    }

}
