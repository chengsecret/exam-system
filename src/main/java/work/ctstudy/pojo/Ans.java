package work.ctstudy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于controller接收前端对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ans {
    private String qid;  //题目号
    private String context; //用户的答案
}
