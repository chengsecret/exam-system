package work.ctstudy.service;

import work.ctstudy.entity.User;

public interface UserService {
    /**
     * 注册
     * @param user
     * @return
     */
    public boolean registerUser(User user);

    /**
     * 修改用户的姓名与班级
     * @param name
     * @param classroom
     * @return
     */
    public boolean updateUserById(String name, String classroom);

    /**
     * 修改用户密码
     * @param password
     * @return
     */
    public boolean UpdateUserPassword(String password, String oldPassword);

    public User selectUserByNum(String num);
}
