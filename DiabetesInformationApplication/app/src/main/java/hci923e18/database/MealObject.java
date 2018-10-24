package hci923e18.database;

import com.orm.SugarRecord;
import java.util.Date;

public class MealObject extends SugarRecord<MealObject>
{
    private Integer _mealtype;
    private String _listMeals;
    private Double _bloodGlucoseLevel, _insulinResult;
    private Date _timestamp;

    public Integer get_mealtype() {
        return _mealtype;
    }

    public void set_mealtype(Integer _mealtype) {
        this._mealtype = _mealtype;
    }

    public String get_listMeals() {
        return _listMeals;
    }

    public void set_listMeals(String _listMeals) {
        this._listMeals = _listMeals;
    }

    public Double get_bloodGlucoseLevel() {
        return _bloodGlucoseLevel;
    }

    public void set_bloodGlucoseLevel(Double _bloodGlucoseLevel) {
        this._bloodGlucoseLevel = _bloodGlucoseLevel;
    }

    public Double get_insulinResult() {
        return _insulinResult;
    }

    public void set_insulinResult(Double _insulinResult) {
        this._insulinResult = _insulinResult;
    }

    public Date get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(Date _timestamp) {
        this._timestamp = _timestamp;
    }

    public MealObject()
    {

    }

    public MealObject(Integer mealType, String listMeals, Double bloodGlucoseLevel, Double insulinResult, Date timestamp)
    {
        _mealtype = mealType;
        _listMeals = listMeals;
        _bloodGlucoseLevel = bloodGlucoseLevel;
        _insulinResult = insulinResult;
        _timestamp = timestamp;
    }
}
