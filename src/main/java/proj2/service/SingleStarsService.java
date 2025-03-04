package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DTO.Stars;
import proj2.repos.SingleStarRepo;

import java.util.List;

@Service
public class SingleStarsService {

    @Autowired
    private final SingleStarRepo singleStarRepo;


    public SingleStarsService(SingleStarRepo singleStarRepo) {
        this.singleStarRepo = singleStarRepo;
    }

    public List<Stars> GetAllStars(String id) {
        return singleStarRepo.getSingleStar(id);
    }

}
