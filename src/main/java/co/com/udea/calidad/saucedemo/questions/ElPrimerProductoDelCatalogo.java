package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ElPrimerProductoDelCatalogo implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return ProductsPage.NOMBRES.resolveAllFor(actor).get(0).getText().trim();
    }

    public static ElPrimerProductoDelCatalogo actual() {
        return new ElPrimerProductoDelCatalogo();
    }
}
