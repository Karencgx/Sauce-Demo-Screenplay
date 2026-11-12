package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/** Valores del resumen: "Item total: $39.98" se responde como "$39.98". */
public class ElResumenDeLaOrden implements Question<String> {

    private final Target etiqueta;

    private ElResumenDeLaOrden(Target etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String answeredBy(Actor actor) {
        return Util.valorDespuesDeDosPuntos(etiqueta.resolveFor(actor).getText());
    }

    public static ElResumenDeLaOrden subtotal() {
        return new ElResumenDeLaOrden(CheckoutPage.SUBTOTAL);
    }

    public static ElResumenDeLaOrden impuesto() {
        return new ElResumenDeLaOrden(CheckoutPage.IMPUESTO);
    }

    public static ElResumenDeLaOrden total() {
        return new ElResumenDeLaOrden(CheckoutPage.TOTAL);
    }
}
