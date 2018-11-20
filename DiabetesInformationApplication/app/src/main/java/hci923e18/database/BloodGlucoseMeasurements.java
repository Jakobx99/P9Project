package hci923e18.database;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class BloodGlucoseMeasurements extends SugarRecord<BloodGlucoseMeasurements> implements Serializable{

    private Long _date;
    private Double _glucoseLevel;
    private Integer _type, _category, _beforeAfter;


    public BloodGlucoseMeasurements(){

    }

    public BloodGlucoseMeasurements(Calendar date, Double glucoseLevel, Integer type, Integer category, Integer beforeAfter){
        setDate(date);
        _glucoseLevel = glucoseLevel;
        _type = type;
        _category = category;               // 0 = morgenmad, 1 = middagsmad, 2 = aftensmad
        _beforeAfter = beforeAfter;         // 0 = ingen markering, 1 = før mad, 2 = efter mad
    }

    public void setDate(Calendar localdate){
        _date = localdate.getTime().getTime();
    }

    public Calendar getDate(){
        Calendar c = Calendar.getInstance();
        Date localDate = new Date();
        localDate.setTime(_date);
        c.setTime(localDate);
        return c;
    }

    private Long get_date() {
        return _date;
    }



    private void set_date(Long _date) {
        this._date = _date;
    }

    public Double get_glucoseLevel() {
        return _glucoseLevel;
    }

    public void set_glucoseLevel(Double _glucoseLevel) {
        this._glucoseLevel = _glucoseLevel;
    }

    public Integer get_type() {
        return _type;
    }

    public void set_type(Integer _type) {
        this._type = _type;
    }

    public Integer get_category() {
        return _category;
    }

    public void set_category(Integer _category) {
        this._category = _category;
    }

    public Integer get_beforeAfter() {
        return _beforeAfter;
    }

    public void set_beforeAfter(Integer _beforeAfter) {
        this._beforeAfter = _beforeAfter;
    }
}
