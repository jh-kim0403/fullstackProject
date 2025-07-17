package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DTO.MetadataResponseDTO;
import proj2.repos.DashboardRepo;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    DashboardRepo dashboardRepo = new DashboardRepo();

    public int addStar(String name, Integer birthYear){
        return dashboardRepo.addStar(name, birthYear);
    }

    public List<MetadataResponseDTO> getMetadata(){
        return dashboardRepo.getMetadata();
    }
}
