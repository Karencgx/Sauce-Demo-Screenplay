package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.interactions.DiligenciarEnvio;
import co.com.udea.calidad.saucedemo.interactions.Pausar;
import co.com.udea.calidad.saucedemo.userinterfaces.CartPage;
import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarCheckout {

    private IniciarCheckout() {}

    /** Desde cualquier pantalla: abre el carrito, pulsa Checkout y diligencia los datos del cliente. */
    public static Performable conDatos(String nombre, String apellido, String codigoPostal) {
        return Task.where("{0} inicia el checkout con los datos del cliente",
                AbrirCarrito.deCompras(),
                Pausar.configurada(),
                ClicSeguro.en(CartPage.CHECKOUT),
                WaitUntil.the(CheckoutPage.NOMBRE, isVisible()).forNoMoreThan(10).seconds(),
                Pausar.configurada(),
                DiligenciarEnvio.con(nombre, apellido, codigoPostal));
    }
}