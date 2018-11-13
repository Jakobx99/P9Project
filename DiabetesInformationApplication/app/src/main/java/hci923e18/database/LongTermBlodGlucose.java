package hci923e18.database;

import com.orm.SugarRecord;

import java.util.Calendar;

public class LongTermBlodGlucose extends SugarRecord<LongTermBlodGlucose> {

    private Long _start;
    private Long _end;
    private Double _value;

    public LongTermBlodGlucose(){

    }

    public LongTermBlodGlucose(Calendar start, Calendar end, Double value){
        setStart(start);
        setEnd(end);
        _value = value;
    }

    public Calendar getStart(){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(_start);
        return c;
    }
    public void setStart(Calendar c){
        _start = c.getTimeInMillis();
    }
    public Calendar getEnd(){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(_end);
        return c;
    }
    public void setEnd(Calendar c){
        _end =c.getTimeInMillis();
    }

    private Long get_start() {
        return _start;
    }

    private void set_start(Long _start) {
        this._start = _start;
    }

    private Long get_end() {
        return _end;
    }

    private void set_end(Long _end) {
        this._end = _end;
    }

    public Double get_value() {
        return _value;
    }

    public void set_value(Double _value) {
        this._value = _value;
    }
}
