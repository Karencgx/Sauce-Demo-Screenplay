package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ElTituloDeLaPagina implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return ProductsPage.TITULO.resolveFor(actor).getText().trim();
    }

    public static ElTituloDeLaPagina actual() {
        return new ElTituloDeLaPagina();
    }
}
