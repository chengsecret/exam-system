package work.ctstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 题目表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    private int qid;
    private int pid; //题目所属试卷
    private int type; //类型，0为选择，1为其它
    private String content; //题目内容
    private String key; //答案
    private int score; //分值，默认为0
}
