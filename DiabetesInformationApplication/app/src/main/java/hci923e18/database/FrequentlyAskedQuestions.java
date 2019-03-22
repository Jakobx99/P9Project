package hci923e18.database;

import com.orm.SugarRecord;

public class FrequentlyAskedQuestions extends SugarRecord {

    private String _title;
    private String _answer;
    private Integer _type;
    private Integer _category;

    public FrequentlyAskedQuestions(){

    }
    public FrequentlyAskedQuestions(String title, String answer, Integer type, Integer category){
        _title = title;
        _answer = answer;
        _type = type;
        _category = category;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_answer() {
        return _answer;
    }

    public void set_answer(String _answer) {
        this._answer = _answer;
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
}
