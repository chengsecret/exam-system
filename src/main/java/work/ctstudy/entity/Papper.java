package work.ctstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 试卷表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Papper implements Serializable {
    private int pid;
    private String title; //试卷名
    private String creator; //发布者
    private String time; //发布时间，有默认值
    private int mins; //考试时间

    private ArrayList<Integer> choice; //试卷中有哪些选择题
    private ArrayList<Integer> fill; //试卷中有哪些填空解答题
}
