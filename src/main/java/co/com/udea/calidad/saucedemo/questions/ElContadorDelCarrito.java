package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.CartPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

/** Número del ícono del carrito. Si el ícono no muestra número, responde 0. */
public class ElContadorDelCarrito implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        List<WebElementFacade> insignia = CartPage.INSIGNIA.resolveAllFor(actor);
        return insignia.isEmpty() ? 0 : Integer.parseInt(insignia.get(0).getText().trim());
    }

    public static ElContadorDelCarrito actual() {
        return new ElContadorDelCarrito();
    }
}
