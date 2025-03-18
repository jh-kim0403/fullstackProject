package proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.DTO.PaymentRequestDTO;
import proj2.DTO.ShoppingCart;
import proj2.repos.PaymentRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepository;

    public PaymentService(PaymentRepo paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public boolean Payment(PaymentRequestDTO paymentRequestDTO) {
        String firstName = paymentRequestDTO.getFirstName();
        String lastName = paymentRequestDTO.getLastName();
        String ccId = paymentRequestDTO.getCreditCardNumber();
        String expirationDate = paymentRequestDTO.getExpirationDate();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = formatter.format(today);
        ShoppingCart[] cart = paymentRequestDTO.getShoppingCart();
        Map<String, String> rs = paymentRepository.validatePayment(firstName, lastName, ccId, expirationDate);

        if(rs.isEmpty()){
            return false;
        } else {
            for (ShoppingCart item : cart){
                for(int i = 0 ; i < item.getQuantity() ; i++){
                    if(paymentRepository.insertSales(rs.get("id"), item.getId(), formattedDate) <= 0){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
