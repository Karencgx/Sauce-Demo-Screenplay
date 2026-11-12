package co.com.udea.calidad.saucedemo.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Espera a que el elemento sea visible, lo centra en pantalla y hace clic por JavaScript.
 * Evita clics perdidos cuando algo tapa el elemento o el navegador pierde el foco.
 */
public class ClicSeguro implements Interaction {

    private final Target objetivo;

    public ClicSeguro(Target objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade elemento = objetivo.resolveFor(actor);
        elemento.waitUntilVisible();
        BrowseTheWeb.as(actor).evaluateJavascript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();",
                elemento.getWrappedElement());
    }

    public static ClicSeguro en(Target objetivo) {
        return Tasks.instrumented(ClicSeguro.class, objetivo);
    }
}
