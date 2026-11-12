package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.userinterfaces.CartPage;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SeguirComprando {

    private SeguirComprando() {}

    public static Performable enElCatalogo() {
        return Task.where("{0} regresa al catálogo para seguir comprando",
                ClicSeguro.en(CartPage.SEGUIR_COMPRANDO),
                WaitUntil.the(ProductsPage.ORDENAMIENTO, isVisible()).forNoMoreThan(10).seconds());
    }
}
