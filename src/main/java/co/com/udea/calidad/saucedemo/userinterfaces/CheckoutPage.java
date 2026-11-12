package co.com.udea.calidad.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    // Paso 1: datos del cliente
    public static final Target NOMBRE = Target.the("campo First Name").locatedBy("[data-test='firstName']");
    public static final Target APELLIDO = Target.the("campo Last Name").locatedBy("[data-test='lastName']");
    public static final Target CODIGO_POSTAL = Target.the("campo Zip/Postal Code").locatedBy("[data-test='postalCode']");
    public static final Target CONTINUAR = Target.the("botón Continue").locatedBy("[data-test='continue']");

    // Paso 2: resumen de la orden
    public static final Target SUBTOTAL = Target.the("subtotal de la orden").locatedBy("[data-test='subtotal-label']");
    public static final Target IMPUESTO = Target.the("impuesto de la orden").locatedBy("[data-test='tax-label']");
    public static final Target TOTAL = Target.the("total de la orden").locatedBy("[data-test='total-label']");
    public static final Target FINALIZAR = Target.the("botón Finish").locatedBy("[data-test='finish']");
    public static final Target CANCELAR = Target.the("botón Cancel").locatedBy("[data-test='cancel']");

    // Confirmación
    public static final Target CONFIRMACION = Target.the("mensaje de confirmación").locatedBy("[data-test='complete-header']");
    public static final Target VOLVER_INICIO = Target.the("botón Back Home").locatedBy("[data-test='back-to-products']");
    public static final Target GENERAR_PDF = Target.the("botón Generate PDF order").locatedBy("//button[contains(.,'Generate PDF')]");

    private CheckoutPage() {}
}
