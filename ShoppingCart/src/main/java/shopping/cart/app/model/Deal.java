package shopping.cart.app.model;

/**
 * Created by Ravi Krish on 09/05/2016.
 */
public class Deal {
    private String productCode;
    private Integer count;

    public Deal(String productCode, Integer count) {
        this.productCode = productCode;
        this.count = count;
    }

    public String getProductCode() {
        return productCode;
    }

    public Integer getCount() {
        return count;
    }
}
