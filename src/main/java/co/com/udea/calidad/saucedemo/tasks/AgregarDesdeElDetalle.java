package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductDetailPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarDesdeElDetalle {

    private AgregarDesdeElDetalle() {}

    public static Performable alCarrito() {
        return Task.where("{0} agrega el producto al carrito desde su detalle",
                ClicSeguro.en(ProductDetailPage.AGREGAR),
                WaitUntil.the(ProductDetailPage.ELIMINAR, isVisible()).forNoMoreThan(5).seconds());
    }
}
