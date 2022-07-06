package work.ctstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 成绩表，用户考试卷，存入成绩表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
    private int gid;
    private int uid; //谁考的
    private int pid; //考的哪张试卷
    private int score; //得分，默认为0
    private int flag; //是否批改，默认0未批，1已批改
}
