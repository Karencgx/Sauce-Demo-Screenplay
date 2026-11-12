package co.com.udea.calidad.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static final Target ICONO = Target.the("ícono del carrito").locatedBy("[data-test='shopping-cart-link']");
    public static final Target INSIGNIA = Target.the("contador del carrito").locatedBy("[data-test='shopping-cart-badge']");
    public static final Target NOMBRES = Target.the("productos dentro del carrito").locatedBy("[data-test='inventory-item-name']");
    public static final Target CHECKOUT = Target.the("botón Checkout").locatedBy("[data-test='checkout']");
    public static final Target SEGUIR_COMPRANDO = Target.the("botón Continue Shopping").locatedBy("[data-test='continue-shopping']");

    private CartPage() {}
}
