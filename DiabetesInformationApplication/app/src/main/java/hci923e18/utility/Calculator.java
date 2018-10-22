package hci923e18.utility;

import com.orm.query.Select;

import hci923e18.database.Profile;

public class Calculator {
    Profile user = Profile.listAll(Profile.class).get(0);

    public Double carbohydratesRelationCalculation()
    {
        // To calculate the carbohydrate-insulin-relation we take 500 divided by the total daily insulin consumption
        Double carbohydratesRelation;
        Double totalDailyInsulinConsumption = user.get_totalDailyInsulinConsumption();

        return carbohydratesRelation = 500/totalDailyInsulinConsumption;
    }

    public Double insulinSesitivityCalculation()
    {
        Double insulinSesitivity;
        Double totalDailyInsulinConsumption = user.get_totalDailyInsulinConsumption();

        return insulinSesitivity = 100/totalDailyInsulinConsumption;

    }

    public Double insulinCalculator(Double carbohydrate, Double bloodGlucoseLevel, Double fiber, Double glucose)
    {

        double carbohydrateRelation = carbohydratesRelationCalculation();
        double insulinSesitivity = insulinSesitivityCalculation();


return 2.0;

    }
}
