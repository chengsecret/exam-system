package work.ctstudy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.ctstudy.entity.Grade;

@Repository
public interface GradeMapper {
    public boolean addGrade(Grade grade);

    public Grade selectGrade(@Param("uid") String uid,@Param("pid") String pid);

    public boolean updateGrade(Grade grade);
}
