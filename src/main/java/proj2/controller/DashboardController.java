package proj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proj2.DTO.MetadataResponseDTO;
import proj2.DTO.StarRequestDTO;
import proj2.service.DashboardService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://54.193.13.146"})
public class DashboardController {

    @Autowired
    DashboardService dashboardService = new DashboardService();

    @PostMapping
    @RequestMapping("/api/dashboard/addstar")
    public int addStar(@RequestBody StarRequestDTO starRequestDTO){
        return dashboardService.addStar(starRequestDTO.getName(), starRequestDTO.getBirthYear());
    }

    @RequestMapping("/api/dashboard/metadata")
    public List<MetadataResponseDTO> getMetadata(){
        return dashboardService.getMetadata();
    }
}
