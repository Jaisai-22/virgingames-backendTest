package com.virgingames.testinfo;

import com.virgingames.stepinfo.VirginGameStep;
import com.virgingames.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.hamcrest.Matchers.greaterThan;
//To run our Serenity tests with JUnit, we need to @RunWith the SerenityRunner, test runner.
// SerenityRunner instruments the step libraries and ensures that the test results will be recorded and reported on by the Serenity reporters.

@RunWith(SerenityRunner.class)
public class VirginGameTest extends TestBase {

    static String defaultGameFrequency;
    static String timeStamp;
    static String startTime;


    @Steps
    VirginGameStep virginGameStep;


    @Title("This test will get all default game frequency = 300000")
    @Test
    public void test001() {
        ValidatableResponse response = virginGameStep.getAllGamesInfo().statusCode(200);
        defaultGameFrequency = response.extract().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}.defaultGameFrequency").toString();
        Assert.assertTrue(defaultGameFrequency.contains("300000"));
        System.out.println("The Games with the defaultGameFrequency are : " + defaultGameFrequency);
    }

    /*
     * This test will verify that the startTime for all Games is always future timestamp.
     * i.e. startTimes are greater than timeStamp.
     */
    @Title("Verifying That the startTime is always future timeStamp")
    @Test
    public void test002() {


        ValidatableResponse response = virginGameStep.getAllGamesInfo()
                .statusCode(200);
        timeStamp = response.extract().path("timestamp").toString();
        startTime = response.extract().path("bingoLobbyInfoResource.streams.startTime").toString();

        Assert.assertThat(startTime, greaterThan(timeStamp));

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The timestamp is : " + timeStamp);
        System.out.println("The startTimes are :" + startTime);
        System.out.println("------------------End of Test---------------------------");
    }
               

}
