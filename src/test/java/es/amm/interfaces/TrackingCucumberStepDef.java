package es.amm.interfaces;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.amm.intrastructure.Event;
import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class TrackingCucumberStepDef extends SpringBootBaseIntegrationTest {

    private final Logger logger = LoggerFactory.getLogger(TrackingCucumberStepDef.class);

    private Shipment shipment;

    private Tracking tracking;

    private Event event;

    @Given("^the provided shipment$")
    public void the_provided_shipment() throws Exception {
        // POST SHIPMENT
        clean();
        shipment = postShipment();
        assertThat(!StringUtils.isEmpty(shipment.getReference())).isTrue();
        tracking = new Tracking();
    }

    @When("^shipment reference should be equal to tracking (\\w+)$")
    public void the_shipment_reference_should_be_equal_to_tracking_reference(final String trackingReference) {
        tracking.setReference(trackingReference);
        assertThat(shipment.getReference().equals(tracking.getReference()));
    }

    @When("^shipment parcel number should be equal to tracking parcel (\\w+)$")
    public void shipment_parcel_number_should_be_equal_to_tracking_parcel_number(final Long trackingTotalParcels) {
        tracking.setParcels(trackingTotalParcels);
        assertThat(shipment.getParcels().size() == tracking.getParcels()).isTrue();
    }

    @When("^shipment total weight should be less than tracking weight (\\w+)$")
    public void shipment_total_weight_should_be_less_than_tracking_weight(final Long trackingTotalWeight) {
        tracking.setWeight(trackingTotalWeight);
        assertThat(shipment.getTotalWeight() < tracking.getWeight()).isTrue();
    }

    @When("^shipment total weight should be greater or equal than tracking weight (\\w+)$")
    public void shipment_total_weight_should_be_greater_or_equal_than_tracking_weight(final Long trackingTotalWeight) {
        tracking.setWeight(trackingTotalWeight);
        assertThat(shipment.getTotalWeight() >= tracking.getWeight()).isTrue();
    }

    @When("^tracking (\\w+) should be DELIVERED$")
    public void tracking_status_should_be(final String trackingStatus) {
        tracking.setStatus(Tracking.STATUS_TRACKING.valueOf(trackingStatus));
        assertThat(tracking.getStatus().equals(Tracking.STATUS_TRACKING.DELIVERED)).isTrue();
    }

    @When("^tracking (\\w+) is not DELIVERED$")
    public void tracking_status_is_not(final String trackingStatus) {
        tracking.setStatus(Tracking.STATUS_TRACKING.valueOf(trackingStatus));
        assertThat(!tracking.getStatus().equals(Tracking.STATUS_TRACKING.DELIVERED)).isTrue();
    }

    @When("^any other tracking field is null$")
    public void any_other_tracking_field_is_null() {
        boolean isAnyEmpty = tracking.isAnyFieldEmptyOtherThanReference();
        assertThat(isAnyEmpty).isTrue();
    }

    @When("^tracking (\\w+) is not equal to shipment reference$")
    public void tracking_reference_is_not_equal_to_shipment_reference(final String trackingReference) {
        tracking.setReference(trackingReference);
        assertThat(!tracking.getReference().equals(shipment.getReference())).isTrue();
    }

    @Then("^dispatch an application event reference (\\w+) and status (\\w+)$")
    public void dispatch_an_application_event(final String reference, final String status) throws Exception {
        event = putTracking(tracking);
        Event expectedEvent = new Event.Builder().setReference(reference).setStatus(Tracking.STATUS_TRACKING.valueOf(status)).build();
        assertThat(event.equals(expectedEvent)).isTrue();
    }

    @And("^print it into the console$")
    public void print_it_into_the_console() {
        logger.info(event.toString());
    }

}