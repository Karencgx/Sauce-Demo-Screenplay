package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Precio que muestra el catálogo para un producto, por ejemplo "$29.99". */
public class ElPrecioDelProductoEnElCatalogo implements Question<String> {

    private final String producto;

    private ElPrecioDelProductoEnElCatalogo(String producto) {
        this.producto = producto;
    }

    @Override
    public String answeredBy(Actor actor) {
        return ProductsPage.PRECIO_PRODUCTO.of(producto).resolveFor(actor).getText().trim();
    }

    public static ElPrecioDelProductoEnElCatalogo de(String producto) {
        return new ElPrecioDelProductoEnElCatalogo(producto);
    }
}