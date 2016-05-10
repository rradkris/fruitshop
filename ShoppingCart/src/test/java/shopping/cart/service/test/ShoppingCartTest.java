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

    @Test
    public void verifyAppleOffer() {
        //ASSIGN
        repeat(Constants.MAGIC_NUMBER_FIVE, () -> {
            cart.addProduct(new Apple(Constants.APPLE_PRODUCT_CODE, Product.Type.APPLE.name(), Constants.APPLE_PRODUCT_PRICE, new Deal(Constants.APPLE_PRODUCT_CODE, Constants.MAGIC_NUMBER_TWO)));
        });

        //ACT
        CheckoutService checkOutService = new CheckoutService();
        checkOutService.checkout(cart);

        //ASSERT
        Assert.assertEquals(new Double(1.8), checkOutService.getPrice());
    }

    @Test
    public void verifyOrangeDeal() {
        //ASSIGN
        repeat(Constants.MAGIC_NUMBER_SEVEN, () -> {
            cart.addProduct(new Orange(Constants.ORANGE_PRODUCT_CODE, Product.Type.ORANGE.name(), Constants.ORANGE_PRODUCT_PRICE, new Deal(Constants.ORANGE_PRODUCT_CODE, Constants.MAGIC_NUMBER_THREE)));
        });

        //ACT
        CheckoutService checkOutService = new CheckoutService();
        checkOutService.checkout(cart);

        //ASSERT
        Assert.assertEquals(new Double(1.25), checkOutService.getPrice());

        //Delete a product
        cart.deleteProduct(0);
        checkOutService.checkout(cart);
        Assert.assertEquals(new Double(1.00), checkOutService.getPrice());
    }

    @Test
    public void verifyDeals() {
        //ASSIGN
        repeat(Constants.MAGIC_NUMBER_NINE, () -> {
            cart.addProduct(new Orange(Constants.ORANGE_PRODUCT_CODE, Product.Type.ORANGE.name(), Constants.ORANGE_PRODUCT_PRICE, new Deal(Constants.ORANGE_PRODUCT_CODE, Constants.MAGIC_NUMBER_THREE)));
            cart.addProduct(new Apple(Constants.APPLE_PRODUCT_CODE, Product.Type.APPLE.name(), Constants.APPLE_PRODUCT_PRICE, new Deal(Constants.APPLE_PRODUCT_CODE, Constants.MAGIC_NUMBER_TWO)));
        });

        //ACT
        CheckoutService checkOutService = new CheckoutService();
        checkOutService.checkout(cart);

        //ASSERT
        Assert.assertEquals(new Double(4.5), checkOutService.getPrice());
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
