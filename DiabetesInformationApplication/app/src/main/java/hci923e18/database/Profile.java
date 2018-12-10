package hci923e18.database;

import com.orm.SugarRecord;

public class Profile extends SugarRecord<Profile> {
    private Double _idealBloodGlucoseLevel;
    private Double _insulinDuration;
    private Double _totalDailyInsulinConsumption;
    private Double _upperBloodGlucoseLevel;
    private Double _lowerBloodGlucoseLevel;
    private Double _beforeBloodGlucoseLevel;
    private Double _afterBloodGlucoseLevel;
    private Integer _parentalControl;
    private Integer _bloodGlucoseMeasurement;
    private Integer _insulinCalc;
    private String _phoneNumber;


    public Profile()
    {

    }

        public Profile(Double idealBloodGlucoseLevel, Double insulinDuration, Double totalDailyInsulinConsumption, Double upperBloodGlucoseLevel, Double lowerBloodGlucoseLevel,
                   Double beforeBloodGlucoseLevel, Double afterBloodGlucoseLevel, Integer parentalControl, Integer bloodGlucoseMeasurment, Integer insulinCalc, String phoneNumber)
    {
        _idealBloodGlucoseLevel = idealBloodGlucoseLevel;
        _insulinDuration = insulinDuration;
        _totalDailyInsulinConsumption = totalDailyInsulinConsumption;
        _afterBloodGlucoseLevel = afterBloodGlucoseLevel;
        _beforeBloodGlucoseLevel = beforeBloodGlucoseLevel;
        _lowerBloodGlucoseLevel = lowerBloodGlucoseLevel;
        _upperBloodGlucoseLevel = upperBloodGlucoseLevel;
        _parentalControl = parentalControl;
        _bloodGlucoseMeasurement = bloodGlucoseMeasurment;
        _insulinCalc = insulinCalc;
        _phoneNumber = phoneNumber;
    }

    public Double get_upperBloodGlucoseLevel() {
        return _upperBloodGlucoseLevel;
    }

    public void set_upperBloodGlucoseLevel(Double _upperBloodGlucoseLevel) {
        this._upperBloodGlucoseLevel = _upperBloodGlucoseLevel;
    }

    public Double get_lowerBloodGlucoseLevel() {
        return _lowerBloodGlucoseLevel;
    }

    public void set_lowerBloodGlucoseLevel(Double _lowerBloodGlucoseLevel) {
        this._lowerBloodGlucoseLevel = _lowerBloodGlucoseLevel;
    }

    public Double get_beforeBloodGlucoseLevel() {
        return _beforeBloodGlucoseLevel;
    }

    public void set_beforeBloodGlucoseLevel(Double _beforeBloodGlucoseLevel) {
        this._beforeBloodGlucoseLevel = _beforeBloodGlucoseLevel;
    }

    public Double get_afterBloodGlucoseLevel() {
        return _afterBloodGlucoseLevel;
    }

    public void set_afterBloodGlucoseLevel(Double _afterBloodGlucoseLevel) {
        this._afterBloodGlucoseLevel = _afterBloodGlucoseLevel;
    }

    public Double get_totalDailyInsulinConsumption() {
        return _totalDailyInsulinConsumption;
    }

    public void set_totalDailyInsulinConsumption(Double _totalDailyInsulinConsumption) {
        this._totalDailyInsulinConsumption = _totalDailyInsulinConsumption;
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

    public Integer get_parentalControl() {
        return _parentalControl;
    }

    public void set_parentalControl(Integer _parentalControl) {
        this._parentalControl = _parentalControl;
    }

    public Integer get_bloodGlucoseMeasurement() {
        return _bloodGlucoseMeasurement;
    }

    public void set_bloodGlucoseMeasurement(Integer _bloodGlucoseMeasurement) {
        this._bloodGlucoseMeasurement = _bloodGlucoseMeasurement;
    }

    public Integer get_insulinCalc() {
        return _insulinCalc;
    }

    public void set_insulinCalc(Integer _insulinCalc) {
        this._insulinCalc = _insulinCalc;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

}
