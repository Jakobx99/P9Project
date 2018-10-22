package hci923e18.database;

import com.orm.SugarRecord;
import java.util.Date;

public class MealObject extends SugarRecord<MealObject>
{
    private Integer _mealtype;
    private String _listMeals;
    private Double _bloodGlucoseLevel, _insulinResult;
    private Date _timestamp;

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
