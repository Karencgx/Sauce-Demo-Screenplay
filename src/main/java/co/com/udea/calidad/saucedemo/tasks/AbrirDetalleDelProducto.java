package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductDetailPage;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AbrirDetalleDelProducto {

    private AbrirDetalleDelProducto() {}

    public static Performable de(String nombre) {
        return Task.where("{0} abre el detalle del producto " + nombre,
                ClicSeguro.en(ProductsPage.NOMBRE_PRODUCTO.of(nombre)),
                WaitUntil.the(ProductDetailPage.VOLVER, isVisible()).forNoMoreThan(10).seconds());
    }
}
