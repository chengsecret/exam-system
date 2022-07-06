package work.ctstudy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import work.ctstudy.entity.User;
import work.ctstudy.service.UserService;
import work.ctstudy.utils.VerifyCodeUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 所以请求都是放行的，无需认证
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user/login";
    }

    /**
     * 返回验证码图片
     *
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("verifyCodePicture")
    public void verifyCodePicture(HttpServletResponse response, HttpSession session) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("code", code); //把验证码存入session

        response.setContentType("imag/png");
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCodeUtils.outputImage(200, 50, outputStream, code);
    }

    /**
     * 注册业务
     *
     * @param user
     * @param code
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(User user, String code, HttpSession session, Model model) {
        //验证码正确，再进行注册
        if (!ObjectUtils.isEmpty(code) && code.equalsIgnoreCase((String) session.getAttribute("code"))) {
            if (userService.registerUser(user)) {
                session.setAttribute("msg", "注册成功！");
                return "redirect:/index";
            } else {
                model.addAttribute("msg", "已存在该学号用户");
                return "register";
            }
        }
        model.addAttribute("msg", "验证码错误");
        return "register";
    }


    /**
     * 登录业务
     *
     * @param num
     * @param password
     * @param code
     * @param session
     * @param pid      如果是要考试的时候登录，会传入pid
     * @return
     */
    @RequestMapping("toLogin")
    public String login(String num, String password, String code, HttpSession session, String pid) {

        //登录前先判断验证码是否正确
        if (!ObjectUtils.isEmpty(code) && code.equalsIgnoreCase((String) session.getAttribute("code"))) {
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(new UsernamePasswordToken(num, password));
                User user = userService.selectUserByNum(num);
                session.setAttribute("LoginUser",user);
                session.setAttribute("name",user.getName());
                //登录成功
                if (!ObjectUtils.isEmpty(pid)) {
                    session.setAttribute("pid", pid);
                    return "redirect:/paper/exam";   //去考试
                } else {
                    session.setAttribute("msg", "登陆成功！");
                    return "redirect:/index";
                }
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                session.setAttribute("msg", "该学号用户不存在");
                return "redirect:/index";
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                session.setAttribute("msg", "密码错误");
                return "redirect:/index";
            }
        }
        session.setAttribute("msg", "验证码错误");
        return "redirect:/index";
    }

    @RequestMapping("info")
    public String info(Model model, HttpSession session) {
        User user = (User) session.getAttribute("LoginUser");
        model.addAttribute("user", user);
        return "student/userInfo";
    }

    @RequestMapping("info1")
    public String info1(Model model,HttpSession session) {
        User user = (User) session.getAttribute("LoginUser");
        model.addAttribute("user", user);
        session.setAttribute("info","");
        return "student/userInfo";
    }

    /**
     * 修改姓名和班级
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("update")
    public String update(User user, HttpSession session) {
        if (userService.updateUserById(user.getName(), user.getClassroom())) {
            return "redirect:/user/logout"; //退出登录
        }else {
            session.setAttribute("info","修改失败!");
            return "redirect:/user/info";
        }
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param code
     * @return
     */
    @RequestMapping("updatePassword")
    public String updatePassword(String oldPassword, String newPassword, String code, HttpSession session) {
        //先验证验证码是否正确
        if (!ObjectUtils.isEmpty(code) && code.equalsIgnoreCase((String) session.getAttribute("code"))){
            if (userService.UpdateUserPassword(newPassword, oldPassword)) {
                return "redirect:/user/logout"; //退出登录
            }else {
                session.setAttribute("info","修改密码失败，请检查原密码是否正确！");
                return "redirect:/user/info";
            }
        }

        session.setAttribute("info","验证码错误!");
        return "redirect:/user/info";
    }
}
