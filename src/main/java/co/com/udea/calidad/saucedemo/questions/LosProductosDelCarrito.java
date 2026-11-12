package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.CartPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.stream.Collectors;

/** Nombres de los productos que aparecen en la página del carrito. */
public class LosProductosDelCarrito implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
        return CartPage.NOMBRES.resolveAllFor(actor).stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public static LosProductosDelCarrito actuales() {
        return new LosProductosDelCarrito();
    }
}
