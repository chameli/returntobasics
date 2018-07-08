package com.chameli.rtb;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTestSteps {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Given("a $width by $height game")
    public void theGameIsRunning(int width, int height) {
        LOGGER.debug("Height {}, width {}", height, width);
    }

    @When("I toggle the cell at ($column, $row)")
    public void iToggleTheCellAt(int column, int row) {
        LOGGER.debug("Column {}, row {}", column, row);
    }

    @Then("the grid should look like $grid")
    public void theGridShouldLookLike(String grid) {
        LOGGER.debug("Grid {}", grid);
    }
}
