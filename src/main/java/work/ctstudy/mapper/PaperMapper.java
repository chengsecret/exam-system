package work.ctstudy.mapper;

import org.springframework.stereotype.Repository;
import work.ctstudy.entity.Papper;

import java.util.List;

@Repository
public interface PaperMapper {
    List<Papper> getAllPapers();

    public Papper getPaperByPid(String pid);
}
