package hci1025f19.database;

import com.orm.SugarRecord;

public class Identifier extends SugarRecord {

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    private String _ID;
    private Boolean _Opgaver;

    public Boolean get_hotfix() {
        return _hotfix;
    }

    public void set_hotfix(Boolean _hotfix) {
        this._hotfix = _hotfix;
    }

    private Boolean _hotfix;

    public Boolean get_opgaver() {
        return _Opgaver;
    }

    public void set_opgaver(Boolean _opgaver) {
        this._Opgaver = _opgaver;
    }



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
