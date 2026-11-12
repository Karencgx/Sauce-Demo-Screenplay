package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VolverAlCatalogo {

    private VolverAlCatalogo() {}

    public static Performable desdeLaConfirmacion() {
        return Task.where("{0} regresa al catálogo desde la confirmación",
                ClicSeguro.en(CheckoutPage.VOLVER_INICIO),
                WaitUntil.the(ProductsPage.ORDENAMIENTO, isVisible()).forNoMoreThan(10).seconds());
    }
}
