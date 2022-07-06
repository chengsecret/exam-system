package work.ctstudy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.ctstudy.entity.User;

@Repository
public interface UserMapper {
    /**
     * 增加一个用户
     * @param user
     * @return
     */
    public boolean addUser(User user);

    /**
     * 通过学号找用户
     * @param num
     * @return
     */
    public User selectUserByNum(String num);

    /**
     * 修改用户的名字，教室
     * @param name
     * @param classroom
     * @return
     */
    public boolean updateUserById(@Param("uid") int id,@Param("name") String name, @Param("classroom") String classroom);

    public boolean updatePasswordById(@Param("uid") int uid, @Param("password") String password, @Param("salt") String salt);
}
