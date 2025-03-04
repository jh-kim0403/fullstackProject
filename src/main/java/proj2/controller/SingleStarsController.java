package proj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj2.DTO.Stars;
import proj2.service.SingleStarsService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SingleStarsController {


    private final SingleStarsService singleStarsService;

    @Autowired
    public SingleStarsController(SingleStarsService service) {
        this.singleStarsService = service;
    }

    @RequestMapping("/singlestar")
    public List<Stars> singleStar(@RequestParam String id) {
        if (id.isEmpty()) {
            System.out.println("Empty name");
        }

        return singleStarsService.GetAllStars(id);
    }
}
