import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by carlos on 5/31/17.
 */
public class MarkIVTest {

    private static class MockCoffeeMakerAPI implements CoffeeMakerAPI {
        private int warmerState = CoffeeMakerAPI.WARMER_OFF;
        private int warmerPlateStatus = CoffeeMakerAPI.WARMER_EMPTY;
        private int boilerState = CoffeeMakerAPI.BOILER_OFF;
        private int brewButtonStatus = CoffeeMakerAPI.BREW_BUTTON_NOT_PUSHED;
        private int boilerStatus = CoffeeMakerAPI.BOILER_EMPTY;

        public void mockBrewButtonStatus(int mockedBrewButtonStatus) {
            this.brewButtonStatus = mockedBrewButtonStatus;
        }

        public int getWarmerState() {
            return this.warmerState;
        }

        public int getWarmerPlateStatus() {
            return this.warmerPlateStatus;
        }

        @Override
        public int getBoilerStatus() {
            return boilerStatus;
        }
        public void setBoilerStatus(int status) {
            boilerStatus = status;
        }


        public int getBrewButtonStatus() {
            return brewButtonStatus;
        }

        public void setBoilerState(int boilerStatus) {
            this.boilerState= boilerState;
        }

        public void setWarmerState(int warmerState) {
            this.warmerState = warmerState;
        }

        public void setWarmerPlateStatus (int warmerPlateStatus) {
            this.warmerPlateStatus = warmerPlateStatus;
        }

        public void setIndicatorState(int indicatorState) {

        }

        public void setReliefValveState(int reliefValveState) {

        }

        public int getBoilerState () {
            return this.boilerState;
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
    public void givenAnMachineWithWaterWithoutFilternWhereIsStartedThenNotOk() {
        MockCoffeeMakerAPI interf = new MockCoffeeMakerAPI();
        MarkIV mark = new MarkIV(interf);
        interf.setBoilerStatus(CoffeeMakerAPI.BOILER_NOT_EMPTY);
        interf.mockBrewButtonStatus(CoffeeMakerAPI.BREW_BUTTON_PUSHED);
        assertFalse(mark.start());
    }

    @Test
    public void givenAnMachineWithWaterWithFilternWhereIsStartedThenOk() {
        MockCoffeeMakerAPI interf = new MockCoffeeMakerAPI();
        MarkIV mark = new MarkIV(interf);
        interf.setBoilerStatus(CoffeeMakerAPI.BOILER_NOT_EMPTY);
        interf.mockBrewButtonStatus(CoffeeMakerAPI.BREW_BUTTON_PUSHED);
        mark.setFilter(new CoffeeFilter());
        assertTrue(mark.start());
    }

    @Test
    public void givenAnMachineWithWaterWithFilterWhereIsStartedThenOkWarmState() {
        MockCoffeeMakerAPI interf = new MockCoffeeMakerAPI();
        MarkIV mark = new MarkIV(interf);
        interf.setBoilerStatus(CoffeeMakerAPI.BOILER_NOT_EMPTY);
        interf.mockBrewButtonStatus(CoffeeMakerAPI.BREW_BUTTON_PUSHED);
        mark.setFilter(new CoffeeFilter()); //TODO check order
        mark.start();
        assertTrue(interf.getWarmerState() == CoffeeMakerAPI.WARMER_ON);
    }

    //TODO two pots
    //TODO stop boiling extract filter
    //TODO TEST without pot
    /*
    // With this implementation is not possible test it
    @Test
    public void givenAnMachineWithWaterWithFilterWhereIsExtractPotWhileBoilingThenStopCorrectly() {
        MockCoffeeMakerAPI interf = new MockCoffeeMakerAPI();
        MarkIV mark = new MarkIV(interf);
        mark.setFilter(new CoffeeFilter());
        interf.setWarmerPlateStatus(CoffeeMakerAPI.POT_EMPTY);
        interf.setBoilerStatus(CoffeeMakerAPI.BOILER_NOT_EMPTY);
        interf.mockBrewButtonStatus(CoffeeMakerAPI.BREW_BUTTON_PUSHED);

        //TODO FORCING CHANGE, bad practices
        assertTrue(interf.getBoilerState()==CoffeeMakerAPI.BOILER_ON);
        mark.start();
        interf.setWarmerPlateStatus(CoffeeMakerAPI.WARMER_EMPTY);
        assertTrue(interf.getBoilerState()==CoffeeMakerAPI.BOILER_OFF);

    }
*/


}
