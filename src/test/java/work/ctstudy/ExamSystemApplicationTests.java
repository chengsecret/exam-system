package work.ctstudy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import work.ctstudy.entity.Answer;
import work.ctstudy.entity.Grade;
import work.ctstudy.entity.Question;
import work.ctstudy.entity.User;
import work.ctstudy.mapper.*;

@SpringBootTest
class ExamSystemApplicationTests {
    @Autowired
    GradeMapper mapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        System.out.println(mapper.selectGrade("1","1"));
    }

}
