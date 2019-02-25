package hci923e18.database;

import com.orm.SugarRecord;

public class Identifier extends SugarRecord<Identifier> {

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    private String _ID;

    public Boolean get_advanced() {
        return _advanced;
    }

    public void set_advanced(Boolean _advanced) {
        this._advanced = _advanced;
    }

    private Boolean _advanced;

    public Identifier(){

    }
    public Identifier(String id, Boolean advanced){
        _ID = id;
        _advanced = advanced;
    }

}
