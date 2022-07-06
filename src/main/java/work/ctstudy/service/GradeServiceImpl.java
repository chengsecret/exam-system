package work.ctstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.ctstudy.entity.Grade;
import work.ctstudy.mapper.GradeMapper;
import work.ctstudy.pojo.Ans;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;


    @Override
    public boolean addGrade(String uid, String pid) {

        //生成成绩表
        if(gradeMapper.selectGrade(uid,pid) == null){
            gradeMapper.addGrade(new Grade(-1,Integer.parseInt(uid),Integer.parseInt(pid),0,0));
        }

        return true;
    }

    @Override
    public boolean updateGrade(Grade grade) {
        return gradeMapper.updateGrade(grade);
    }

    @Override
    public Grade selectGrade(String uid, String pid) {
        return gradeMapper.selectGrade(uid,pid);
    }
}
