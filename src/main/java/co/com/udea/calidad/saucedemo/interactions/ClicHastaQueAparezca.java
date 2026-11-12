package co.com.udea.calidad.saucedemo.interactions;

import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Hace clic en un elemento y confirma que apareció el resultado esperado.
 * Si el clic se perdió (el elemento sigue ahí y no pasó nada), lo repite hasta 3 veces.
 */
public class ClicHastaQueAparezca implements Interaction {

    private static final int INTENTOS = 3;

    private final Target clic;
    private final Target esperado;

    public ClicHastaQueAparezca(Target clic, Target esperado) {
        this.clic = clic;
        this.esperado = esperado;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (int i = 1; i <= INTENTOS; i++) {
            WebElementFacade boton = clic.resolveFor(actor);
            try {
                boton.waitUntilVisible();
            } catch (RuntimeException e) {
                throw new IllegalStateException("No apareció " + clic.getName() + ". URL="
                        + BrowseTheWeb.as(actor).getDriver().getCurrentUrl(), e);
            }
            BrowseTheWeb.as(actor).evaluateJavascript(
                    "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();",
                    boton.getWrappedElement());
            Util.dormir(1000);
            if (esperado.resolveFor(actor).isCurrentlyVisible()) {
                return;
            }
            if (!clic.resolveFor(actor).isCurrentlyVisible()) {
                break; // la página cambió, solo falta que cargue lo esperado
            }
        }
        esperado.resolveFor(actor).waitUntilVisible();
    }

    public static ClicHastaQueAparezca en(Target clic, Target esperado) {
        return Tasks.instrumented(ClicHastaQueAparezca.class, clic, esperado);
    }
}