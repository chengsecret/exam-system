package work.ctstudy.service;

import work.ctstudy.pojo.Ans;

public interface AnswerService {

    /**
     * 添加答题内容，并返回该题得分，
     * @param uid
     * @param pid
     * @param ans
     * @return
     */
    public int addAnswerReturnScore(String uid, String pid, Ans ans);
}
