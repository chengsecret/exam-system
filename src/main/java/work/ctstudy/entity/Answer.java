package work.ctstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户的答题情况表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer implements Serializable {
    private int aid;
    private int gid; // 属于哪张成绩表
    private int qid; // 属于哪到题目
    private String commit; //用户 提交的内容
    private int flag; // 是否批改，默认为0未批改
    private int score; //得分默认为0
}
