package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.ProductDetailPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ElDetalleDelProducto implements Question<String> {

    private final Target dato;

    private ElDetalleDelProducto(Target dato) {
        this.dato = dato;
    }

    @Override
    public String answeredBy(Actor actor) {
        return dato.resolveFor(actor).getText().trim();
    }

    public static ElDetalleDelProducto nombre() {
        return new ElDetalleDelProducto(ProductDetailPage.NOMBRE);
    }

    public static ElDetalleDelProducto precio() {
        return new ElDetalleDelProducto(ProductDetailPage.PRECIO);
    }
}
