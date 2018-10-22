package hci923e18.database;

import com.orm.SugarRecord;

public class Profile extends SugarRecord<Profile> {

    private Double _weight, _insulinSensitivity, _carbohydratesRelation, _idealBloodGlucoseLevel, _insulinDuration;

    public Profile()
    {

    }

    public Profile(Double weight, Double insulinSensitivity, Double carbohydratesRelation, Double idealBloodGlucoseLevel, Double insulinDuration)
    {
        _weight = weight;
        _insulinSensitivity = insulinSensitivity;
        _carbohydratesRelation = carbohydratesRelation;
        _idealBloodGlucoseLevel = idealBloodGlucoseLevel;
        _insulinDuration = insulinDuration;
    }

}
