package co.com.udea.calidad.saucedemo.stepdefinitions;

import co.com.udea.calidad.saucedemo.questions.ElMensajeDeError;
import co.com.udea.calidad.saucedemo.questions.ElTituloDeLaPagina;
import co.com.udea.calidad.saucedemo.questions.LaPantallaDeLogin;
import co.com.udea.calidad.saucedemo.tasks.AbrirLaTienda;
import co.com.udea.calidad.saucedemo.tasks.CerrarSesion;
import co.com.udea.calidad.saucedemo.tasks.IngresarSinSesion;
import co.com.udea.calidad.saucedemo.tasks.IniciarSesion;
import co.com.udea.calidad.saucedemo.utils.Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/** HU-01 and HU-02: log in, log out and error messages. The password is never written in the features. */
public class AuthenticationStepDefinitions {

    @Given("^the customer opens the SauceDemo store$")
    public void theCustomerOpensTheStore() {
        theActorInTheSpotlight().attemptsTo(AbrirLaTienda.saucedemo());
    }

    @Given("^the customer is logged in as \"([^\"]*)\"$")
    public void theCustomerIsLoggedInAs(String username) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.conExito(username, Util.password()));
    }

    @When("^the customer logs in as \"([^\"]*)\"$")
    public void theCustomerLogsInAs(String username) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.con(username, Util.password()));
    }

    @When("^the customer logs in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void theCustomerLogsInWith(String username, String password) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.con(username, password));
    }

    @When("^the customer logs out from the menu$")
    public void theCustomerLogsOut() {
        theActorInTheSpotlight().attemptsTo(CerrarSesion.desdeElMenu());
    }

    @When("^the customer tries to open the catalog page directly without logging in$")
    public void theCustomerOpensTheCatalogWithoutSession() {
        theActorInTheSpotlight().attemptsTo(IngresarSinSesion.alCatalogoPorUrl());
    }

    @Then("^the customer sees the product catalog$")
    public void theCustomerSeesTheCatalog() {
        theActorInTheSpotlight().should(seeThat(ElTituloDeLaPagina.actual(), equalTo("Products")));
    }

    @Then("^the system shows the error message \"([^\"]*)\"$")
    public void theSystemShowsTheErrorMessage(String message) {
        theActorInTheSpotlight().should(seeThat(ElMensajeDeError.mostrado(), equalTo(message)));
    }

    @Then("^the customer is back on the login screen$")
    public void theCustomerIsBackOnTheLoginScreen() {
        theActorInTheSpotlight().should(seeThat(LaPantallaDeLogin.visible(), equalTo(true)));
    }
}
