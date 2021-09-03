package com.bookit.step_definitions;

import com.bookit.utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class DBStepDefs {

    Map<String, Object> dbMap;

    @When("User sends a query to DataBase with {string}")
    public void user_sends_a_query_to_DataBase_with(String email) {
        String query = "select firstname, lastname, role, name, location, batch_number\n" +
                "from users u join (select t.id, name, batch_number, location \n" +
                "from team t join campus c on t.campus_id=c.id) r\n" +
                "on u.team_id = r.id where u.email = '"+email+"';";

        dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);
    }

    @Then("User gets DataBase information")
    public void user_gets_DataBase_information() {

    }

}
