package proj2.DTO;

public class ShoppingCart {
    private String id;
    private String title;
    private int quantity;
    private double price;
    public ShoppingCart() {

    }

    public ShoppingCart(String id, String title, int quantity, double price) {
        super();
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
