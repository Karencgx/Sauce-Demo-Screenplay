package co.com.udea.calidad.saucedemo.stepdefinitions;

import co.com.udea.calidad.saucedemo.questions.ElContadorDelCarrito;
import co.com.udea.calidad.saucedemo.questions.ElDetalleDelProducto;
import co.com.udea.calidad.saucedemo.questions.ElMensajeDeConfirmacion;
import co.com.udea.calidad.saucedemo.questions.ElMensajeDeError;
import co.com.udea.calidad.saucedemo.questions.ElPdfDeLaOrden;
import co.com.udea.calidad.saucedemo.questions.ElPrecioDelProductoEnElCatalogo;
import co.com.udea.calidad.saucedemo.questions.ElPrimerProductoDelCatalogo;
import co.com.udea.calidad.saucedemo.questions.ElResumenDeLaOrden;
import co.com.udea.calidad.saucedemo.questions.ElTituloDeLaPagina;
import co.com.udea.calidad.saucedemo.questions.LaCantidadDeProductosDelCatalogo;
import co.com.udea.calidad.saucedemo.questions.LaPantallaDeLogin;
import co.com.udea.calidad.saucedemo.questions.LosProductosDelCarrito;
import co.com.udea.calidad.saucedemo.tasks.AbrirCarrito;
import co.com.udea.calidad.saucedemo.tasks.AbrirDetalleDelProducto;
import co.com.udea.calidad.saucedemo.tasks.AbrirLaTienda;
import co.com.udea.calidad.saucedemo.tasks.AgregarDesdeElDetalle;
import co.com.udea.calidad.saucedemo.tasks.AgregarProductos;
import co.com.udea.calidad.saucedemo.tasks.CancelarCompra;
import co.com.udea.calidad.saucedemo.tasks.CerrarSesion;
import co.com.udea.calidad.saucedemo.tasks.EliminarProducto;
import co.com.udea.calidad.saucedemo.tasks.FinalizarCompra;
import co.com.udea.calidad.saucedemo.tasks.GenerarPdfDeLaOrden;
import co.com.udea.calidad.saucedemo.tasks.IngresarSinSesion;
import co.com.udea.calidad.saucedemo.tasks.IniciarCheckout;
import co.com.udea.calidad.saucedemo.tasks.IniciarSesion;
import co.com.udea.calidad.saucedemo.tasks.OrdenarCatalogo;
import co.com.udea.calidad.saucedemo.tasks.SeguirComprando;
import co.com.udea.calidad.saucedemo.tasks.VolverAlCatalogo;
import co.com.udea.calidad.saucedemo.utils.Util;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

/**
 * Actor principal: Karen (cliente de la tienda). Cada paso de Gherkin delega
 * en una Task (acción de negocio) o en una Question (verificación).
 */
public class StepDefinition {

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Karen");
    }

    /** Pausa entre paso y paso para poder observar la prueba (se cambia con -Ppausa.ms=3000). */
    @AfterStep
    public void pausaEntrePasos() {
        Util.dormir();
    }

    // ------------------------------------------------------------------ Autenticación

    @Dado("^(?:que )?el cliente abre la tienda SauceDemo$")
    public void elClienteAbreLaTienda() {
        theActorInTheSpotlight().attemptsTo(AbrirLaTienda.saucedemo());
    }

    @Dado("^(?:que )?el cliente inició sesión con el usuario \"([^\"]*)\" y la contraseña \"([^\"]*)\"$")
    public void elClienteInicioSesion(String usuario, String clave) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.conExito(usuario, clave));
    }

    @Cuando("^inicia sesión con el usuario \"([^\"]*)\" y la contraseña \"([^\"]*)\"$")
    public void iniciaSesion(String usuario, String clave) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.con(usuario, clave));
    }

    @Cuando("^cierra sesión desde el menú$")
    public void cierraSesion() {
        theActorInTheSpotlight().attemptsTo(CerrarSesion.desdeElMenu());
    }

    @Cuando("^intenta entrar directamente a la página del catálogo sin iniciar sesión$")
    public void entraSinSesion() {
        theActorInTheSpotlight().attemptsTo(IngresarSinSesion.alCatalogoPorUrl());
    }

    @Entonces("^el cliente ve el catálogo de productos$")
    public void veElCatalogo() {
        theActorInTheSpotlight().should(seeThat(ElTituloDeLaPagina.actual(), equalTo("Products")));
    }

    @Entonces("^el sistema muestra el mensaje de error \"([^\"]*)\"$")
    public void muestraMensajeDeError(String mensaje) {
        theActorInTheSpotlight().should(seeThat(ElMensajeDeError.mostrado(), equalTo(mensaje)));
    }

    @Entonces("^el cliente regresa a la pantalla de inicio de sesión$")
    public void regresaAlLogin() {
        theActorInTheSpotlight().should(seeThat(LaPantallaDeLogin.visible(), equalTo(true)));
    }

    // ------------------------------------------------------------------ Catálogo

    @Entonces("^el catálogo muestra (\\d+) productos$")
    public void catalogoMuestraProductos(int cantidad) {
        theActorInTheSpotlight().should(seeThat(LaCantidadDeProductosDelCatalogo.actual(), equalTo(cantidad)));
    }

    @Cuando("^ordena el catálogo por \"([^\"]*)\"$")
    public void ordenaElCatalogo(String criterio) {
        theActorInTheSpotlight().attemptsTo(OrdenarCatalogo.por(criterio));
    }

    @Entonces("^el primer producto del catálogo es \"([^\"]*)\"$")
    public void primerProducto(String nombre) {
        theActorInTheSpotlight().should(seeThat(ElPrimerProductoDelCatalogo.actual(), equalTo(nombre)));
    }

    @Cuando("^abre el detalle del producto \"([^\"]*)\"$")
    public void abreElDetalle(String nombre) {
        theActorInTheSpotlight().attemptsTo(AbrirDetalleDelProducto.de(nombre));
    }

    @Entonces("^el detalle muestra el nombre \"([^\"]*)\"$")
    public void detalleMuestraNombre(String nombre) {
        theActorInTheSpotlight().should(seeThat(ElDetalleDelProducto.nombre(), equalTo(nombre)));
    }

    @Entonces("^el detalle muestra el precio \"([^\"]*)\"$")
    public void detalleMuestraPrecio(String precio) {
        theActorInTheSpotlight().should(seeThat(ElDetalleDelProducto.precio(), equalTo(precio)));
    }

    @Cuando("^agrega al carrito el producto desde el detalle$")
    public void agregaDesdeElDetalle() {
        theActorInTheSpotlight().attemptsTo(AgregarDesdeElDetalle.alCarrito());
    }

    @Entonces("^el precio de \"([^\"]*)\" en el catálogo es \"([^\"]*)\"$")
    public void precioEnElCatalogo(String producto, String precio) {
        theActorInTheSpotlight().should(seeThat(ElPrecioDelProductoEnElCatalogo.de(producto), equalTo(precio)));
    }

    // ------------------------------------------------------------------ Carrito

    @Cuando("^(?:que )?agrega al carrito los productos \"([^\"]*)\" y \"([^\"]*)\"$")
    public void agregaDosProductos(String primero, String segundo) {
        theActorInTheSpotlight().attemptsTo(AgregarProductos.alCarrito(primero, segundo));
    }

    @Cuando("^(?:que )?agrega al carrito el producto \"([^\"]*)\"$")
    public void agregaUnProducto(String nombre) {
        theActorInTheSpotlight().attemptsTo(AgregarProductos.alCarrito(nombre));
    }

    @Cuando("^elimina del carrito el producto \"([^\"]*)\"$")
    public void eliminaProducto(String nombre) {
        theActorInTheSpotlight().attemptsTo(EliminarProducto.delCarrito(nombre));
    }

    @Cuando("^abre su carrito de compras$")
    public void abreElCarrito() {
        theActorInTheSpotlight().attemptsTo(AbrirCarrito.deCompras());
    }

    @Cuando("^regresa al catálogo para seguir comprando$")
    public void seguirComprando() {
        theActorInTheSpotlight().attemptsTo(SeguirComprando.enElCatalogo());
    }

    @Entonces("^el carrito muestra (\\d+) productos?$")
    public void carritoMuestraProductos(int cantidad) {
        theActorInTheSpotlight().attemptsTo(AbrirCarrito.deCompras());
        theActorInTheSpotlight().should(seeThat(LosProductosDelCarrito.actuales(), hasSize(cantidad)));
    }

    @Entonces("^el carrito no muestra ningún producto$")
    public void carritoVacio() {
        theActorInTheSpotlight().attemptsTo(AbrirCarrito.deCompras());
        theActorInTheSpotlight().should(seeThat(LosProductosDelCarrito.actuales(), hasSize(0)));
    }

    @Entonces("^el producto \"([^\"]*)\" aparece en el carrito$")
    public void productoEnElCarrito(String nombre) {
        theActorInTheSpotlight().should(seeThat(LosProductosDelCarrito.actuales(), hasItem(nombre)));
    }

    @Entonces("^el ícono del carrito muestra el número (\\d+)$")
    public void iconoMuestraNumero(int numero) {
        theActorInTheSpotlight().should(seeThat(ElContadorDelCarrito.actual(), equalTo(numero)));
    }

    @Entonces("^el ícono del carrito no muestra ningún número$")
    public void iconoSinNumero() {
        theActorInTheSpotlight().should(seeThat(ElContadorDelCarrito.actual(), equalTo(0)));
    }

    // ------------------------------------------------------------------ Checkout

    @Cuando("^inicia el checkout con nombre \"([^\"]*)\", apellido \"([^\"]*)\" y código postal \"([^\"]*)\"$")
    public void iniciaElCheckout(String nombre, String apellido, String codigoPostal) {
        theActorInTheSpotlight().attemptsTo(IniciarCheckout.conDatos(nombre, apellido, codigoPostal));
    }

    @Cuando("^finaliza la compra$")
    public void finalizaLaCompra() {
        theActorInTheSpotlight().attemptsTo(FinalizarCompra.deLaOrden());
    }

    @Cuando("^cancela la compra$")
    public void cancelaLaCompra() {
        theActorInTheSpotlight().attemptsTo(CancelarCompra.deLaOrden());
    }

    @Cuando("^regresa al catálogo desde la confirmación$")
    public void regresaDesdeLaConfirmacion() {
        theActorInTheSpotlight().attemptsTo(VolverAlCatalogo.desdeLaConfirmacion());
    }

    @Cuando("^genera el PDF de la orden$")
    public void generaElPdf() {
        theActorInTheSpotlight().attemptsTo(GenerarPdfDeLaOrden.descargable());
    }

    @Entonces("^el cliente ve el resumen de la orden$")
    public void veElResumenDeLaOrden() {
        theActorInTheSpotlight().should(seeThat(ElTituloDeLaPagina.actual(), equalTo("Checkout: Overview")));
    }

    @Entonces("^el sistema muestra el mensaje de confirmación \"([^\"]*)\"$")
    public void muestraConfirmacion(String mensaje) {
        theActorInTheSpotlight().should(seeThat(ElMensajeDeConfirmacion.deLaOrden(), equalTo(mensaje)));
    }

    @Entonces("^el resumen de la orden muestra el subtotal \"([^\"]*)\"$")
    public void resumenSubtotal(String valor) {
        theActorInTheSpotlight().should(seeThat(ElResumenDeLaOrden.subtotal(), equalTo(valor)));
    }

    @Entonces("^el resumen de la orden muestra el impuesto \"([^\"]*)\"$")
    public void resumenImpuesto(String valor) {
        theActorInTheSpotlight().should(seeThat(ElResumenDeLaOrden.impuesto(), equalTo(valor)));
    }

    @Entonces("^el resumen de la orden muestra el total \"([^\"]*)\"$")
    public void resumenTotal(String valor) {
        theActorInTheSpotlight().should(seeThat(ElResumenDeLaOrden.total(), equalTo(valor)));
    }

    @Entonces("^el PDF de la orden se descarga en el equipo$")
    public void pdfDescargado() {
        theActorInTheSpotlight().should(seeThat(ElPdfDeLaOrden.descargado(), equalTo(true)));
    }
}