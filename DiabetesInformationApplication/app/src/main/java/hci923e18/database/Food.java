package hci923e18.database;

import com.orm.SugarRecord;

public class Food extends SugarRecord<Food> {

    private String _name;
    private Double _carbohydrate, _protein, _fiber, _sugar;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Double get_carbohydrate() {
        return _carbohydrate;
    }

    public void set_carbohydrate(Double _carbohydrate) {
        this._carbohydrate = _carbohydrate;
    }

    public Double get_protein() {
        return _protein;
    }

    public void set_protein(Double _protein) {
        this._protein = _protein;
    }

    public Double get_fiber() {
        return _fiber;
    }

    public void set_fiber(Double _fiber) {
        this._fiber = _fiber;
    }

    public Double get_sugar() {
        return _sugar;
    }

    public void set_sugar(Double _sugar) {
        this._sugar = _sugar;
    }

    public Food()
    {

    }

    public Food(String name, Double carbohydrate, Double protein, Double fiber, Double sugar)
    {
        _name = name;
        _carbohydrate = carbohydrate;
        _protein = protein;
        _fiber = fiber;
        _sugar = sugar;
    }
}
