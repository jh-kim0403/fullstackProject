package proj2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proj2.DTO.PaymentRequestDTO;
import proj2.service.PaymentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {


    private PaymentService paymentService;

    @Autowired
    public  PaymentController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @RequestMapping("/payment")
    public boolean MakePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        return paymentService.Payment(paymentRequestDTO);
    }
}
