package work.ctstudy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ctstudy.entity.Papper;
import work.ctstudy.mapper.PaperMapper;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private PaperMapper paperMapper;

    /**
     * 判断是否认证
     * @return
     */
    @RequestMapping("isAuthenticated")
    @ResponseBody
    public String isAuthenticated() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "authenticated";
        }else return "";
    }

    @RequestMapping("/")
    public String index1(Model model) {
        List<Papper> allPapers = paperMapper.getAllPapers();
        model.addAttribute("papers",allPapers);
        return "index";
    }

    @RequestMapping("/index")
    public String index2(Model model) {
        List<Papper> allPapers = paperMapper.getAllPapers();
        model.addAttribute("papers",allPapers);
        return "index";
    }

    @RequestMapping("/index.html")
    public String index3(Model model, HttpSession session) {
        List<Papper> allPapers = paperMapper.getAllPapers();
        model.addAttribute("papers",allPapers);
        session.setAttribute("msg","");
        return "index";
    }
}
