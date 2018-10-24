package hci923e18.utility;
import android.util.Pair;

import hci923e18.database.Food;
import hci923e18.database.Profile;

public class Calculator {
    private Profile user = new Profile();

    /**
     * Default constructor
     */
    public Calculator()
    {
        try {
            user = Profile.listAll(Profile.class).get(0);
        } catch (Exception e) {
            user.set_idealBloodGlucoseLevel(6.0);
            user.set_weight(80.0);
            user.set_insulinDuration(3.5);
            user.set_totalDailyInsulinConsumption(41.6);
        }
    }

    /**
     * To calculate the carbohydrate-insulin-relation we take 500 divided by the total daily insulin consumption
     * @return Double value representing carbohydrate relation
     */
    private Double carbohydratesRelationCalculation()
    {
        Double totalDailyInsulinConsumption = user.get_totalDailyInsulinConsumption();

        return 500/totalDailyInsulinConsumption;
    }

    /**
     * To calculate the insulin sensitivity meaning how much the blood glucose decreases per unit
     * we take 100 divided by the total daily insulin consumption
     * @return Double value representing insulin sensitivity
     */
    private Double insulinSensitivityCalculation()
    {
        Double totalDailyInsulinConsumption = user.get_totalDailyInsulinConsumption();

        return 100/totalDailyInsulinConsumption;
    }

    /**
     * To calculate how much insulin to take, to get the blood glucose level back to "normal"
     * we take the current blood glucose level, subtract it from the target glucose level and divide it by the insulin sensitivity
     * @param bloodGlucoseLevel Double representing the measured blood glucose level
     * @return Double representing the amount of insulin needed to adjust blood glucose level
     */
    public Double bloodGlucoseGoalCalculation(Double bloodGlucoseLevel)
    {
        Double bloodGlucoseGoal = user.get_idealBloodGlucoseLevel();
        Double differenceBloodGlucoseLevel = bloodGlucoseLevel-bloodGlucoseGoal;
        return differenceBloodGlucoseLevel/insulinSensitivityCalculation();
    }

    /**
     * To calculate how much insulin to take based on the carbohydrate consumption
     * we divide the carbohydrate by the carbohydrate relation and account for the current blood glucose level.
     * @param carbohydrate Double representing the amount of carbohydrate
     * @param bloodGlucoseLevel Double representing the measured blood glucose level
     * @return Double representing the amount of insulin needed to account for the carbohydrates and adjust of blood glucose level
     */
    public Double insulinCalculator(Double carbohydrate, Double bloodGlucoseLevel)
    {
        double carbohydrateRelation = carbohydratesRelationCalculation();
        return carbohydrate/carbohydrateRelation + bloodGlucoseGoalCalculation(bloodGlucoseLevel);

    }

    /**
     * To calculate the percentage of fibers in the carbohydrates, we take the amount of fiber divided by the total amount of carbohydrate divided by 100
     * @param carbohydrate Double representing the amount of carbohydrate
     * @param fiber Double representing the amount of fiber
     * @return Double representing the amount of carbohydrate that is fiber in percentages
     */
    public Double fiberPercentage(Double carbohydrate, Double fiber)
    {
        return fiber/(carbohydrate/100);
    }

    /**
     * To calculate the percentage of sugar in the carbohydrates, we take the amount of sugar divided by the total amount of carbohydrate divided by 100
     * @param carbohydrate Double representing the amount of carbohydrate
     * @param sugar Double representing the amount of sugar
     * @return Double representing the amount of carbohydrate that is sugar in percentages
     */
    public Double sugarPercentage(Double carbohydrate, Double sugar)
    {
        return sugar/(carbohydrate/100);
    }

    /**
     * Converts the nutritional values of a Food object so it corresponds with the amount selected
     * @param pair A pair consisting of the Food object and the amount
     * @return A adjusted Food object
     */
    public Food calculateNutritinalValuesDependingOnWeight(Pair<Food, Double> pair){
        Food f = new Food();

        f.set_name(pair.first.get_name());
        f.set_carbohydrate(pair.first.get_carbohydrate()/100*pair.second);
        f.set_fiber(pair.first.get_fiber()/100*pair.second);
        f.set_protein(pair.first.get_protein()/100*pair.second);
        f.set_sugar(pair.first.get_sugar()/100*pair.second);

        return f;
    }



}
