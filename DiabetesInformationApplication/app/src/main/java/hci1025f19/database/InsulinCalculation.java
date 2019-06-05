package hci1025f19.database;

import com.orm.SugarRecord;

public class InsulinCalculation extends SugarRecord {

    public Double get_result() {
        return _result;
    }

    public void set_result(Double _result) {
        this._result = _result;
    }

    public Double get_carbohydrates() {
        return _carbohydrates;
    }

    public void set_carbohydrates(Double _carbohydrates) {
        this._carbohydrates = _carbohydrates;
    }

    public Double get_bloodGlycoseMeasurement() {
        return _bloodGlycoseMeasurement;
    }

    public void set_bloodGlycoseMeasurement(Double _bloodGlycoseMeasurement) {
        this._bloodGlycoseMeasurement = _bloodGlycoseMeasurement;
    }

    public Double get_fiber() {
        return _fiber;
    }

    public void set_fiber(Double _fiber) {
        this._fiber = _fiber;
    }

    public Long get_date() {
        return _date;
    }

    public void set_date(Long _date) {
        this._date = _date;
    }



    private Double _result;
    private Double _carbohydrates;
    private Double _bloodGlycoseMeasurement;
    private Double _fiber;
    private Long _date;


    public InsulinCalculation(){

    };

    public InsulinCalculation(Double _result, Double _carbohydrates, Double _bloodGlycoseMeasurement, Double _fiber, Long _date) {
        this._result = _result;
        this._carbohydrates = _carbohydrates;
        this._bloodGlycoseMeasurement = _bloodGlycoseMeasurement;
        this._fiber = _fiber;
        this._date = _date;
    }

}
