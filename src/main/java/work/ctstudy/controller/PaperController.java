package work.ctstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ctstudy.entity.Papper;
import work.ctstudy.service.PaperService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @RequestMapping("getPapers")
    @ResponseBody
    public List<Papper> getPappers() {
        return paperService.getAllPapers();
    }

    /**
     * 返回一张试卷的题目信息
     * @param pid
     * @param session
     * @return
     */
    @RequestMapping("exam")
    public String exam(String pid, HttpSession session, Model model) {
        if (pid != null) {
            session.setAttribute("pid",pid); //如果不是模态框登录，就会传pid
        }

        String id = (String) session.getAttribute("pid"); //考的是哪张试卷
        if (id == null) {
            session.setAttribute("msg","请先选择一张试卷，再进行考试");
            return "redirect:/index";
        }

        Papper paper = paperService.getPaperByPid(id);
        if (paper == null) {
            session.setAttribute("msg","请先选择一张试卷，再进行考试");
            return "redirect:/index";
        }
        model.addAttribute("paper",paper);

        return "student/exam";
    }

}
