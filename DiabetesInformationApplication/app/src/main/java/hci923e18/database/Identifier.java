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
    private Boolean _Opgaver;

    public Boolean get_opgaver() {
        return _Opgaver;
    }

    public void set_opgaver(Boolean _opgaver) {
        this._Opgaver = _opgaver;
    }



    public Identifier(){

    }
    public Identifier(String id){
        _ID = id;
    }

}
