package work.ctstudy.service;

import work.ctstudy.entity.Papper;

import java.util.List;

public interface PaperService {
    public List<Papper> getAllPapers();

    public Papper getPaperByPid(String pid);
}
