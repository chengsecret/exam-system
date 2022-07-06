package work.ctstudy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("teacher")
public class TeacherPaperController {

    /**
     * 试卷管理
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("paper")
    public String paper(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("teacher")) {





            model.addAttribute("open","paper");
            return "teacher/paper";
        }

        session.setAttribute("msg","没有权限！");
        return "redirect:/index";
    }
}
