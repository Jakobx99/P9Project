package hci923e18.database;

import com.orm.SugarRecord;

public class Food extends SugarRecord<Food> {

    private String _name;
    private Double _carbohydrate, _protein, _fiber, _sugar;

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
