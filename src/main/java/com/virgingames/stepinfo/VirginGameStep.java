package com.virgingames.stepinfo;

import com.virgingames.constant.EndPoints;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class VirginGameStep {
    /*
     * Defining the steps, and method for getting all the Games information
     * And parsing the text/plain into Json format
     *Steps contain a group of resource manipulation operations.
     * It can be an action, verification or a context related operation.
     * The classic Given_When_Then format can be reflected in the steps.
    */


    @Step("Getting all games information")
    public ValidatableResponse getAllGamesInfo() {

        return SerenityRest.rest()
                .given()
                .log()
                .all()
                .when()
                .get(EndPoints.GET_BINGO)
                .then().log().ifValidationFails()
                .parser("text/plain;charset=UTF-8", Parser.JSON);
    }

}



