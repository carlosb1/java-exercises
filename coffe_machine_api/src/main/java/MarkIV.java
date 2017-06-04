/**
 * Created by carlos on 5/31/17.
 */
public class MarkIV {

    private CoffeeMakerAPI coffeeMakerAPI;
    private Filter filter;

    public MarkIV(CoffeeMakerAPI coffeeMakerAPI) {
        this.coffeeMakerAPI = coffeeMakerAPI;
        this.filter = null;
    }

    public boolean start() {
        boolean result = hasItWater() && pressedBrewButton() && hasFilter();
        coffeeMakerAPI.setBoilerState(CoffeeMakerAPI.BOILER_ON);
        coffeeMakerAPI.setWarmerState(CoffeeMakerAPI.WARMER_ON);
        return result;
    }

    private boolean hasFilter() {
        return filter != null;
    }

    private boolean hasItWater() {
        return coffeeMakerAPI.getBoilerStatus() == CoffeeMakerAPI.BOILER_NOT_EMPTY;
    }

    private boolean pressedBrewButton() {
        return coffeeMakerAPI.getBrewButtonStatus() == CoffeeMakerAPI.BREW_BUTTON_PUSHED;
    }

    public void setFilter(CoffeeFilter filter) {
        this.filter = filter;
    }
}
