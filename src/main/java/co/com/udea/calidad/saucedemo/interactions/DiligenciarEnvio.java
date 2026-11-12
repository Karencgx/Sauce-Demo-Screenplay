package co.com.udea.calidad.saucedemo.interactions;

import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import co.com.udea.calidad.saucedemo.userinterfaces.MensajesPage;
import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Diligencia el formulario de envío y pulsa Continue.
 * Escribe siempre con teclado real (selecciona todo y reemplaza) para que la página registre el valor.
 * Después del clic comprueba el resultado: si la página avanzó, termina; si quedó en el formulario
 * con el error que corresponde a los datos enviados, termina; en cualquier otro caso vuelve a empezar
 * (hasta 4 intentos).
 */
public class DiligenciarEnvio implements Interaction {

    private static final int INTENTOS = 4;

    private static final String FIJAR_VALOR =
            "var el = arguments[0], v = arguments[1];"
            + "var set = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;"
            + "set.call(el, ''); el.dispatchEvent(new Event('input', {bubbles: true}));"
            + "set.call(el, v); el.dispatchEvent(new Event('input', {bubbles: true}));"
            + "el.dispatchEvent(new Event('change', {bubbles: true}));";

    private boolean reforzar = true;

    private final String nombre;
    private final String apellido;
    private final String codigoPostal;

    public DiligenciarEnvio(String nombre, String apellido, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String errorEsperado = errorEsperado();
        reforzar = !usuarioConDefectosEnElFormulario(actor);
        for (int intento = 1; intento <= INTENTOS; intento++) {
            escribir(actor, CheckoutPage.NOMBRE, nombre);
            escribir(actor, CheckoutPage.APELLIDO, apellido);
            escribir(actor, CheckoutPage.CODIGO_POSTAL, codigoPostal);
            Util.dormir(500);

            WebElementFacade continuar = CheckoutPage.CONTINUAR.resolveFor(actor);
            continuar.waitUntilVisible();
            BrowseTheWeb.as(actor).evaluateJavascript(
                    "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();",
                    continuar.getWrappedElement());
            Util.dormir(1500);

            if (!CheckoutPage.NOMBRE.resolveFor(actor).isCurrentlyVisible()) {
                return; // la página avanzó al resumen
            }
            if (errorEsperado != null && MensajesPage.ERROR.resolveFor(actor).isCurrentlyVisible()
                    && errorEsperado.equals(MensajesPage.ERROR.resolveFor(actor).getText().trim())) {
                return; // el error es el que corresponde a los datos enviados
            }
        }
        throw new IllegalStateException("El formulario de envío no avanzó tras " + INTENTOS
                + " intentos. " + estado(actor));
    }

    private String estado(Actor actor) {
        String url = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        String error = MensajesPage.ERROR.resolveFor(actor).isCurrentlyVisible()
                ? MensajesPage.ERROR.resolveFor(actor).getText() : "(sin mensaje de error)";
        return "URL=" + url + " | mensaje=" + error;
    }

    /** Con problem_user y error_user no se refuerza: el defecto del formulario debe quedar a la vista. */
    private boolean usuarioConDefectosEnElFormulario(Actor actor) {
        String usuario;
        try {
            usuario = actor.recall("usuario");
        } catch (RuntimeException e) {
            return false;
        }
        return "problem_user".equals(usuario) || "error_user".equals(usuario);
    }

    private String errorEsperado() {
        if (nombre.isEmpty()) return "Error: First Name is required";
        if (apellido.isEmpty()) return "Error: Last Name is required";
        if (codigoPostal.isEmpty()) return "Error: Postal Code is required";
        return null;
    }

    private void escribir(Actor actor, Target campo, String texto) {
        WebElementFacade facade = campo.resolveFor(actor);
        facade.waitUntilVisible();
        WebElement elemento = facade.getWrappedElement();
        elemento.click();
        elemento.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        elemento.sendKeys(texto.isEmpty() ? Keys.DELETE.toString() : texto);
        // Refuerzo: React solo registra el valor si recibe el evento 'input' desde su propio setter.
        if (reforzar) {
            BrowseTheWeb.as(actor).evaluateJavascript(FIJAR_VALOR, elemento, texto);
        }
        Util.dormir(250);
    }

    public static DiligenciarEnvio con(String nombre, String apellido, String codigoPostal) {
        return Tasks.instrumented(DiligenciarEnvio.class, nombre, apellido, codigoPostal);
    }
}