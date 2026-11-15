package co.com.udea.calidad.saucedemo.stepdefinitions;

import co.com.udea.calidad.saucedemo.utils.Util;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/** Prepares the stage before every scenario and pauses between steps so the run can be followed. */
public class Hooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Juan");
    }

    /** Pause between steps (change it with -Ppausa.ms=3000). */
    @AfterStep
    public void pauseBetweenSteps() {
        Util.dormir();
    }
}