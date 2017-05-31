/**
 * Created by carlos on 5/31/17.
 */
public class MarkIV {

    private CoffeeMakerAPI coffeeMakerAPI;
    public MarkIV(CoffeeMakerAPI coffeeMakerAPI) {
        this.coffeeMakerAPI = coffeeMakerAPI;
    }

    public boolean start() {
        //TODO abstract these classes
        return coffeeMakerAPI.getBoilerStatus() == CoffeeMakerAPI.BOILER_NOT_EMPTY && coffeeMakerAPI.getBrewButtonStatus() == CoffeeMakerAPI.BREW_BUTTON_PUSHED;
    }

}
