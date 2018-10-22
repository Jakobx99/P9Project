package hci923e18.database;

import com.orm.SugarRecord;

public class Profile extends SugarRecord<Profile> {

    private Double _weight, _idealBloodGlucoseLevel, _insulinDuration, _totalDailyInsulinConsumption;

    public Profile()
    {

    }

    public Profile(Double weight, Double idealBloodGlucoseLevel, Double insulinDuration, Double totalDailyInsulinConsumption)
    {
        _weight = weight;
        _idealBloodGlucoseLevel = idealBloodGlucoseLevel;
        _insulinDuration = insulinDuration;
        _totalDailyInsulinConsumption = totalDailyInsulinConsumption;
    }

    public Double get_totalDailyInsulinConsumption() {
        return _totalDailyInsulinConsumption;
    }

    public void set_totalDailyInsulinConsumption(Double _totalDailyInsulinConsumption) {
        this._totalDailyInsulinConsumption = _totalDailyInsulinConsumption;
    }

    public Double get_weight() {
        return _weight;
    }

    public void set_weight(Double _weight) {
        this._weight = _weight;
    }

    public Double get_idealBloodGlucoseLevel() {
        return _idealBloodGlucoseLevel;
    }

    public void set_idealBloodGlucoseLevel(Double _idealBloodGlucoseLevel) {
        this._idealBloodGlucoseLevel = _idealBloodGlucoseLevel;
    }

    public Double get_insulinDuration() {
        return _insulinDuration;
    }

    public void set_insulinDuration(Double _insulinDuration) {
        this._insulinDuration = _insulinDuration;
    }
}
