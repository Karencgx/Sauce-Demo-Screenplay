package co.com.udea.calidad.saucedemo.interactions;

import co.com.udea.calidad.saucedemo.questions.ElPdfDeLaOrden;
import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * Pulsa "Generate PDF order" con clic nativo (una descarga necesita un clic de usuario real).
 * El PDF puede quedar como archivo en la carpeta de descargas o abrirse en una pestaña del navegador
 * (depende del perfil de Chrome); se acepta cualquiera de los dos y se repite el clic hasta 3 veces.
 */
public class DescargarPdf implements Interaction {

    public static final String CLAVE_PESTANA = "pdfEnPestana";

    private static final int INTENTOS = 3;
    private static final long ESPERA_POR_INTENTO_MS = 8_000;

    @Override
    public <T extends Actor> void performAs(T actor) {
        long inicio = actor.recall(MarcarInicioDeDescarga.CLAVE);
        Set<String> previos = actor.recall(MarcarInicioDeDescarga.PDF_PREVIOS);
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        int ventanasAntes = driver.getWindowHandles().size();
        for (int i = 1; i <= INTENTOS; i++) {
            WebElementFacade boton = CheckoutPage.GENERAR_PDF.resolveFor(actor);
            boton.waitUntilVisible();
            boton.click();
            long limite = System.currentTimeMillis() + ESPERA_POR_INTENTO_MS;
            while (System.currentTimeMillis() < limite) {
                if (ElPdfDeLaOrden.hayPdfNuevo(inicio, previos, 500)) {
                    return;
                }
                String url = driver.getCurrentUrl();
                if (driver.getWindowHandles().size() > ventanasAntes
                        || url.startsWith("blob:") || url.toLowerCase().contains(".pdf")) {
                    System.out.println("PDF: el navegador abrió el PDF en una pestaña (ventanas="
                            + driver.getWindowHandles().size() + ", url=" + url + ")");
                    actor.remember(CLAVE_PESTANA, true);
                    return;
                }
            }
            System.out.println("PDF: intento " + i + " sin resultado (ventanas="
                    + driver.getWindowHandles().size() + ", url=" + driver.getCurrentUrl() + ")");
        }
    }

    public static DescargarPdf hastaQueAparezca() {
        return Tasks.instrumented(DescargarPdf.class);
    }
}