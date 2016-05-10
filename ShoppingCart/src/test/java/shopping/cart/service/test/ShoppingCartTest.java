package shopping.cart.service.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import shopping.cart.app.model.*;
import shopping.cart.app.service.CheckoutService;
import shopping.cart.app.service.ShoppingCart;

/**
 * Created by Ravi Radhakrishnan on 09/05/2016.
 */
public class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    public void verifyCartWorkingWithoutDeal() {
        //ASSIGN
        repeat(Constants.MAGIC_NUMBER_TWO, () -> {
            cart.addProduct(new Orange(Constants.ORANGE_PRODUCT_CODE, Product.Type.ORANGE.name(), Constants.ORANGE_PRODUCT_PRICE, null));
            cart.addProduct(new Apple(Constants.APPLE_PRODUCT_CODE, Product.Type.APPLE.name(), Constants.APPLE_PRODUCT_PRICE, null));
        });

        //ACT
        CheckoutService checkOutService = new CheckoutService();
        checkOutService.checkout(cart);

        //ASSERT
        Assert.assertEquals(new Double(1.7), checkOutService.getPrice());
    }

    @After
    public void tearDown() {
        cart = null;
    }

    private void repeat(int n, Runnable r) {
        for (int i = 0; i < n; i++)
            r.run();
    }
}
