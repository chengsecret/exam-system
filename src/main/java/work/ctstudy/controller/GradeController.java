package work.ctstudy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ctstudy.entity.Grade;
import work.ctstudy.entity.User;
import work.ctstudy.mapper.GradeMapper;
import work.ctstudy.pojo.Ans;
import work.ctstudy.pojo.ScoreOfGrade;
import work.ctstudy.service.AnswerService;
import work.ctstudy.service.GradeService;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private AnswerService answerService;


    @RequestMapping("submit")
    @ResponseBody
    public String submit(String answer, HttpSession session) throws JsonProcessingException {
        User user = (User) session.getAttribute("LoginUser");

        ObjectMapper mapper = new ObjectMapper();
        Ans[] ans = mapper.readValue(answer, Ans[].class);

        //添加成绩表
        gradeService.addGrade(Integer.toString(user.getUid()),(String) session.getAttribute("pid"));

        int score=0; //得分
        for (Ans an : ans) {
            score += answerService.addAnswerReturnScore(Integer.toString(user.getUid()),(String) session.getAttribute("pid"),an);
        }

        //修改成绩表的得分
        Grade grade = gradeService.selectGrade(Integer.toString(user.getUid()), (String) session.getAttribute("pid"));
        grade.setScore(score);
        gradeService.updateGrade(grade);

        session.setAttribute("msg","考试完成！");
        return Integer.toString(score);
    }

}
