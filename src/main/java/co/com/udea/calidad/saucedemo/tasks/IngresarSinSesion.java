package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class IngresarSinSesion {

    private IngresarSinSesion() {}

    public static Performable alCatalogoPorUrl() {
        return Task.where("{0} intenta entrar al catálogo sin haber iniciado sesión",
                Open.url(Util.URL_BASE + "inventory.html"));
    }
}
