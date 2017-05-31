import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by carlos on 5/31/17.
 */
public class MarkIVTest {

    private static class MockCoffeeMakerAPI implements CoffeeMakerAPI {
        private int warmerPlateStatus = CoffeeMakerAPI.WARMER_EMPTY;
        private int boilerStatus = CoffeeMakerAPI.BOILER_EMPTY;
        private int brewButtonStatus = CoffeeMakerAPI.BREW_BUTTON_NOT_PUSHED;

        public void mockBrewButtonStatus(int mockedBrewButtonStatus) {
            this.brewButtonStatus = mockedBrewButtonStatus;
        }

        public int getWarmerPlateStatus() {
            return warmerPlateStatus;
        }

        public int getBoilerStatus() {
            return boilerStatus;
        }

        public int getBrewButtonStatus() {
            return brewButtonStatus;
        }

        public void setBoilerState(int boilerStatus) {
            this.boilerStatus = boilerStatus;
        }

        public void setWarmerState(int warmerState) {
            this.warmerPlateStatus = warmerState;
        }

        public void setIndicatorState(int indicatorState) {

        }

        public void setReliefValveState(int reliefValveState) {

        }
    }

    @Test
    public void givenAnMachineWhereItDoesNotStartedThenNotOk() {
        MockCoffeeMakerAPI interf = new MockCoffeeMakerAPI();
        MarkIV mark = new MarkIV(interf);
        assertFalse(mark.start());
    }

    @Test
    public void givenAnMachineWithoutWaterWhereIsEmptyThenNotOk() {
        MockCoffeeMakerAPI interf = new MockCoffeeMakerAPI();
        MarkIV mark = new MarkIV(interf);
        interf.mockBrewButtonStatus(CoffeeMakerAPI.BREW_BUTTON_PUSHED);
        assertFalse(mark.start());
        interf.mockBrewButtonStatus(CoffeeMakerAPI.BREW_BUTTON_NOT_PUSHED);
    }
    @Test
    public void givenAnMachineWithWaterAnWhereIsStartedThenOk() {
        MockCoffeeMakerAPI interf = new MockCoffeeMakerAPI();
        MarkIV mark = new MarkIV(interf);
        interf.setBoilerState(CoffeeMakerAPI.BOILER_NOT_EMPTY);
        interf.mockBrewButtonStatus(CoffeeMakerAPI.BREW_BUTTON_PUSHED);
        assertTrue(mark.start());
    }

}
