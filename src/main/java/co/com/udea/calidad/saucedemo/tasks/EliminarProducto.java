package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EliminarProducto {

    private EliminarProducto() {}

    public static Performable delCarrito(String nombre) {
        String slug = Util.slug(nombre);
        return Task.where("{0} elimina del carrito el producto " + nombre,
                ClicSeguro.en(ProductsPage.BOTON_ELIMINAR.of(slug)),
                WaitUntil.the(ProductsPage.BOTON_AGREGAR.of(slug), isVisible()).forNoMoreThan(5).seconds());
    }
}
