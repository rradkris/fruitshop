package shopping.cart.app.service;

import shopping.cart.app.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ravi Krish on 09/05/2016.
 */
public class ShoppingCart {
    private List<Product> cartList = new ArrayList<Product>();
    private Map<String, Integer> productCount = new HashMap<String, Integer>();

    public void addProduct(Product product) {
        cartList.add(product);
    }

    public void deleteProduct(int index) {
        cartList.remove(index);
    }

    public Double getTotalCartValue() {
        return cartList.stream().collect(Collectors.summingDouble(Product::getPrice));

    }

    public List<Product> getProducts() {
        return cartList;
    }
}
