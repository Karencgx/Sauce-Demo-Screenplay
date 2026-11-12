package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicHastaQueAparezca;
import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class FinalizarCompra {

    private FinalizarCompra() {}

    public static Performable deLaOrden() {
        return Task.where("{0} finaliza la compra",
                ClicHastaQueAparezca.en(CheckoutPage.FINALIZAR, CheckoutPage.CONFIRMACION));
    }
}