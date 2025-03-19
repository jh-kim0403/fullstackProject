package proj2.DTO;

public class PaymentRequestDTO {
    private String firstName;
    private String lastName;
    private String creditCardNumber;
    private String expirationDate;
    private ShoppingCart[] shoppingCart;


    public PaymentRequestDTO(String firstName, String lastName, String creditCardNumber, String expirationDate, ShoppingCart[] shoppingCart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.shoppingCart = shoppingCart;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public ShoppingCart[] getShoppingCart() {
        return shoppingCart;
    }
}
