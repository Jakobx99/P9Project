package hci923e18.database;

import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class MealObject extends SugarRecord<MealObject>
{
    private String _mealtype;
    private String _listMeals;
    private Double _bloodGlucoseLevel, _insulinResult, _totalCarbs, _totalFiber, _totalWeight;
    private String _timestamp;

    @Ignore
    private List<Pair<Food, Double>> pairList;


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

    public String get_mealtype() {
        return _mealtype;
    }

    public void set_mealtype(String _mealtype) {
        this._mealtype = _mealtype;
    }

    public List<Pair<Food, Double>> get_meals(){
        String s = get_listMeals();
        List<Pair<Food,Double>> p = new Gson().fromJson(s,new TypeToken<List<Pair<Food,Double>>>(){}.getType());
        return p;
    }

    public void set_meals(List<Pair<Food,Double>> p){
        String s = new Gson().toJson(p);
        set_listMeals(s);
    }


    private String get_listMeals() {
        return _listMeals;
    }

    private void set_listMeals(String _listMeals) {
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

    public String get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }

    public Double get_totalCarbs() {
        return _totalCarbs;
    }

    public void set_totalCarbs(Double _totalCarbs) {
        this._totalCarbs = _totalCarbs;
    }

    public Double get_totalFiber() {
        return _totalFiber;
    }

    public void set_totalFiber(Double _totalFiber) {
        this._totalFiber = _totalFiber;
    }

    public Double get_totalWeight() {
        return _totalWeight;
    }

    public void set_totalWeight(Double _totalWeight) {
        this._totalWeight = _totalWeight;
    }

    public MealObject(String mealType, String listMeals, Double bloodGlucoseLevel, Double insulinResult, String timestamp, Double totalCarbs, Double totalFiber, Double totalWeight)
    {
        _mealtype = mealType;
        _listMeals = listMeals;
        _bloodGlucoseLevel = bloodGlucoseLevel;
        _insulinResult = insulinResult;
        _timestamp = timestamp;
        _totalCarbs = totalCarbs;
        _totalFiber = totalFiber;
        _totalWeight = totalWeight;
    }
}
