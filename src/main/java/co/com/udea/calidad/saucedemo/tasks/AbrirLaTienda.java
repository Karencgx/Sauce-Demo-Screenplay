package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirLaTienda {

    private AbrirLaTienda() {}

    public static Performable saucedemo() {
        return Task.where("{0} abre la tienda SauceDemo", Open.url(Util.URL_BASE));
    }
}
