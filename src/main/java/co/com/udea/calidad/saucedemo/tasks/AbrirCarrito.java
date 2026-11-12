package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AbrirCarrito {

    private AbrirCarrito() {}

    /** Abre el carrito y espera a que la página del carrito esté cargada (el botón Checkout solo existe ahí). */
    public static Performable deCompras() {
        return Task.where("{0} abre su carrito de compras",
                ClicSeguro.en(CartPage.ICONO),
                WaitUntil.the(CartPage.CHECKOUT, isVisible()).forNoMoreThan(10).seconds());
    }
}
