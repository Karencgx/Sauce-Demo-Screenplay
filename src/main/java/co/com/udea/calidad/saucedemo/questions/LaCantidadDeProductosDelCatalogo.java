package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LaCantidadDeProductosDelCatalogo implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return ProductsPage.PRODUCTOS.resolveAllFor(actor).size();
    }

    public static LaCantidadDeProductosDelCatalogo actual() {
        return new LaCantidadDeProductosDelCatalogo();
    }
}
