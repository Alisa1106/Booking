package steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class BookingSteps {
    String city;

    /**
     * User is looking for hotels in minsk city.
     *
     * @param city the city
     */
    @Given("User is looking for hotels in {string} city")
    public void userIsLookingForHotelsInMinskCity(String city) {
        this.city = city;
    }

    /**
     * User does search.
     */
    @When("User does search")
    public void userDoesSearch() {
        open("https://www.booking.com/");
        $(By.id("ss")).sendKeys(city);
        $(".sb-searchbox__button").click();
    }

    /**
     * Hotel hostel urban should be on the search results page.
     *
     * @param hotel the hotel
     */
    @Then("Hotel {string} should be on the search results page")
    public void hotelHostelUrbanShouldBeOnTheSearchResultsPage(String hotel) {
        $x("//span[contains(text(),'Показать цены')]").shouldBe(Condition.visible);
        assertThat($$(".sr-hotel__name").texts(), hasItem(hotel));
    }

    /**
     * Hotel hostel urban rating is.
     *
     * @param hotel  the hotel
     * @param rating the rating
     */
    @Then("Hotel {string} rating is {string}")
    public void hotelHostelUrbanRatingIs(String hotel, String rating) {
        assertThat($x(String.format("//*[contains(text(),'%s')]/ancestor::*[contains(@class,'sr_item')]//*[contains(@class,'bui-review-score__badge')]",
                hotel)).getText(), is(rating));
    }
}