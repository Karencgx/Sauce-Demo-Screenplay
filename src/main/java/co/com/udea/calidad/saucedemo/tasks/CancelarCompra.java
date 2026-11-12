package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;

public class CancelarCompra {

    private CancelarCompra() {}

    public static Performable deLaOrden() {
        return Task.where("{0} cancela la compra", ClicSeguro.en(CheckoutPage.CANCELAR));
    }
}
