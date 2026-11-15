package co.com.udea.calidad.saucedemo.stepdefinitions;

import co.com.udea.calidad.saucedemo.questions.ElMensajeDeConfirmacion;
import co.com.udea.calidad.saucedemo.questions.ElPdfDeLaOrden;
import co.com.udea.calidad.saucedemo.questions.ElResumenDeLaOrden;
import co.com.udea.calidad.saucedemo.questions.ElTituloDeLaPagina;
import co.com.udea.calidad.saucedemo.tasks.CancelarCompra;
import co.com.udea.calidad.saucedemo.tasks.FinalizarCompra;
import co.com.udea.calidad.saucedemo.tasks.GenerarPdfDeLaOrden;
import co.com.udea.calidad.saucedemo.tasks.IniciarCheckout;
import co.com.udea.calidad.saucedemo.tasks.VolverAlCatalogo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/** HU-06, HU-07 and HU-08: shipping data, order summary, confirmation and PDF. */
public class CheckoutStepDefinitions {

    @When("^the customer starts the checkout with first name \"([^\"]*)\", last name \"([^\"]*)\" and postal code \"([^\"]*)\"$")
    public void theCustomerStartsTheCheckout(String firstName, String lastName, String postalCode) {
        theActorInTheSpotlight().attemptsTo(IniciarCheckout.conDatos(firstName, lastName, postalCode));
    }

    @When("^the customer finishes the purchase$")
    public void theCustomerFinishesThePurchase() {
        theActorInTheSpotlight().attemptsTo(FinalizarCompra.deLaOrden());
    }

    @When("^the customer cancels the purchase$")
    public void theCustomerCancelsThePurchase() {
        theActorInTheSpotlight().attemptsTo(CancelarCompra.deLaOrden());
    }

    @When("^the customer returns to the catalog from the confirmation$")
    public void theCustomerReturnsToTheCatalog() {
        theActorInTheSpotlight().attemptsTo(VolverAlCatalogo.desdeLaConfirmacion());
    }

    @When("^the customer generates the order PDF$")
    public void theCustomerGeneratesTheOrderPdf() {
        theActorInTheSpotlight().attemptsTo(GenerarPdfDeLaOrden.descargable());
    }

    @Then("^the customer sees the order summary$")
    public void theCustomerSeesTheOrderSummary() {
        theActorInTheSpotlight().should(seeThat(ElTituloDeLaPagina.actual(), equalTo("Checkout: Overview")));
    }

    @Then("^the system shows the confirmation message \"([^\"]*)\"$")
    public void theSystemShowsTheConfirmation(String message) {
        theActorInTheSpotlight().should(seeThat(ElMensajeDeConfirmacion.deLaOrden(), equalTo(message)));
    }

    @Then("^the order summary shows the subtotal \"([^\"]*)\"$")
    public void theSummaryShowsTheSubtotal(String value) {
        theActorInTheSpotlight().should(seeThat(ElResumenDeLaOrden.subtotal(), equalTo(value)));
    }

    @Then("^the order summary shows the tax \"([^\"]*)\"$")
    public void theSummaryShowsTheTax(String value) {
        theActorInTheSpotlight().should(seeThat(ElResumenDeLaOrden.impuesto(), equalTo(value)));
    }

    @Then("^the order summary shows the total \"([^\"]*)\"$")
    public void theSummaryShowsTheTotal(String value) {
        theActorInTheSpotlight().should(seeThat(ElResumenDeLaOrden.total(), equalTo(value)));
    }

    @Then("^the order PDF is downloaded$")
    public void theOrderPdfIsDownloaded() {
        theActorInTheSpotlight().should(seeThat(ElPdfDeLaOrden.descargado(), equalTo(true)));
    }
}
