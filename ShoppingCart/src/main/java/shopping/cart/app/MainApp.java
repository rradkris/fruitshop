package shopping.cart.app;

import shopping.cart.app.model.Apple;
import shopping.cart.app.model.Constants;
import shopping.cart.app.model.Deal;
import shopping.cart.app.model.Orange;
import shopping.cart.app.model.Product.Type;
import shopping.cart.app.service.CheckoutService;
import shopping.cart.app.service.ShoppingCart;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by Ravi Krish on 09/05/2016.
 */
public class MainApp {
    public static void main(String args[]) {
        // NOTE : THIS MAIN APP IS JUST USED FOR DEMO WITH DEALS
        // NOTE : JUNITS WRITTEN FOR COVER VARIOUS USE CASES
        Scanner sc = new Scanner(System.in);
        System.out.println(" ****************Welcome to the AppleOrange Fruit Shop*************** ");
        System.out.println("Please Enter the number of APPLES : ");
        Integer appleCount = sc.nextInt();
        System.out.println("Please Enter the number of ORANGES :");
        Integer orangeCount = sc.nextInt();

        ShoppingCart shoppingCart = new ShoppingCart();
        //Create Products and add in the shopping cart
        repeat(appleCount, () -> {
            shoppingCart.addProduct(new Apple(Constants.APPLE_PRODUCT_CODE, Type.APPLE.name(), 0.60D, new Deal(Constants.APPLE_PRODUCT_CODE, Constants.MAGIC_NUMBER_TWO)));
        });

        repeat(orangeCount, () -> {
            shoppingCart.addProduct(new Orange(Constants.ORANGE_PRODUCT_CODE, Type.ORANGE.name(), 0.25D, new Deal(Constants.ORANGE_PRODUCT_CODE, Constants.MAGIC_NUMBER_THREE)));
        });

        CheckoutService checkOutService = new CheckoutService();

        checkOutService.checkout(shoppingCart);
        System.out.println("Total Pay : "+ checkOutService.getPrice());
        System.out.println("********************Thank you for Shopping***************************");
    }

    static void repeat(int n, Runnable r) {
        for (int i = 0; i < n; i++)
            r.run();
    }
}
