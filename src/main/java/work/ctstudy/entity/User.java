package work.ctstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int uid;
    private String salt; //盐
    private String password; //密码
    private String name; //姓名
    private String num; //学号
    private String classroom; //班级
    private String role; //角色，默认值为student

}
