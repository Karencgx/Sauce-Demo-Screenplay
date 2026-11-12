package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.DescargarPdf;
import co.com.udea.calidad.saucedemo.interactions.MarcarInicioDeDescarga;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class GenerarPdfDeLaOrden {

    private GenerarPdfDeLaOrden() {}

    public static Performable descargable() {
        return Task.where("{0} genera el PDF de la orden",
                MarcarInicioDeDescarga.ahora(),
                DescargarPdf.hastaQueAparezca());
    }
}