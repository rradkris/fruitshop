package shopping.cart.app.model;

/**
 * Created by Ravi Krish on 09/05/2016.
 */
public abstract class Product implements java.io.Serializable {

    private Double price;
    private String code;
    private String name;
    private Deal deal;

    public Product(String code, String name, Double price, Deal deal) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.deal = deal;
    }

    public Double getPrice() {
        return price;
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Deal getDeal() {
        return deal;
    }

    public enum Type {
        APPLE, ORANGE
    }
}
