package hci923e18.database;

import com.orm.SugarRecord;

public class NoteObject extends SugarRecord
{
    private String _title;
    private String _context;
    private String _timestamp;

    public NoteObject(){

    }

    public NoteObject(String title, String context){
        _title = title;
        _context = context;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_context() {
        return _context;
    }

    public void set_context(String _context) {
        this._context = _context;
    }

    public String get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }
}
