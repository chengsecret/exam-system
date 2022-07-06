package work.ctstudy.service;

import work.ctstudy.entity.Grade;
import work.ctstudy.pojo.Ans;

public interface GradeService {

    /**
     * 保存用户的答题表
     * @param uid
     * @param pid
     * @return
     */
    public boolean addGrade(String uid, String pid);

    public boolean updateGrade(Grade grade);

    public Grade selectGrade(String uid, String pid);
}
