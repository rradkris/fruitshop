package shopping.cart.app.service;

import shopping.cart.app.model.Product;
import shopping.cart.app.model.Product.Type;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ravi Krish on 09/05/2016.
 */
public class ServiceHelper {
    public static Double calculateOffer(ShoppingCart cart) {
        Double discountPrice = 0.0;
        List<Product> dealProducts = cart.getProducts().stream().filter(p -> p.getDeal() != null).collect(Collectors.toList());
        if (dealProducts != null && dealProducts.size() > 0) {
            for (Type type : Type.values()) {
                List<Product> productByType = dealProducts.stream()
                        .filter(product -> product.getName().equalsIgnoreCase(type.name())).collect(Collectors.toList());
                if (productByType.size() > 0) {
                    Product product = productByType.get(0);
                    discountPrice += (productByType.size() / product.getDeal().getCount()) * product.getPrice();
                }
            }
        }
       return discountPrice;
    }
}