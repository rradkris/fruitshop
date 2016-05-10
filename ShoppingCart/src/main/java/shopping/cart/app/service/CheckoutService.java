package shopping.cart.app.service;

/**
 * Created by Ravi Krish on 09/05/2016.
 */
public class CheckoutService {

    private Double totalPrice;

    public void checkout(ShoppingCart cart) {
        totalPrice = cart.getTotalCartValue();
        totalPrice = totalPrice - ServiceHelper.calculateOffer(cart);
    }

    public Double getPrice() {
        return new Double(String.format("%.2f", totalPrice));
    }
}


