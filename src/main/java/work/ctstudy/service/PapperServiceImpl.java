package work.ctstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.ctstudy.entity.Papper;
import work.ctstudy.mapper.PaperMapper;

import java.util.List;

@Service
public class PapperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;

    /**
     * redis
     * @return
     */
    @Override
    public List<Papper> getAllPapers() {

        return paperMapper.getAllPapers();
    }

    @Override
    public Papper getPaperByPid(String pid) {
        return paperMapper.getPaperByPid(pid);
    }

}
