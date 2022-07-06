package work.ctstudy.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import work.ctstudy.entity.User;
import work.ctstudy.mapper.UserMapper;
import work.ctstudy.utils.VerifyCodeUtils;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean registerUser(User user) {
        if (user != null && !ObjectUtils.isEmpty(user.getNum())) {//学号不能为空
            if (userMapper.selectUserByNum(user.getNum()) == null) { //学号必为唯一，数据库中有了，就不能注册
                String salt = VerifyCodeUtils.generateVerifyCode(8); //生成随机盐
                user.setSalt(salt);
                Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);//密码加密
                user.setPassword(md5Hash.toHex());
                if(userMapper.addUser(user)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean updateUserById(String name, String classroom) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("LoginUser");
        return userMapper.updateUserById(user.getUid(),name,classroom);
    }

    @Override
    public boolean UpdateUserPassword(String password, String oldPassword) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("LoginUser");
        Md5Hash md5Hash = new Md5Hash(oldPassword, user.getSalt(), 1024);
        if (md5Hash.toHex().equals(user.getPassword())) { //原密码输入正确
            String salt = VerifyCodeUtils.generateVerifyCode(8);
            Md5Hash newPassword = new Md5Hash(password, salt, 1024);
            if(userMapper.updatePasswordById(user.getUid(),newPassword.toHex(),salt)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User selectUserByNum(String num) {
        return userMapper.selectUserByNum(num);
    }

}
