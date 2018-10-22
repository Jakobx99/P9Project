package hci923e18.database;

import com.orm.SugarRecord;

public class Person extends SugarRecord<Person> {

    private String firstname;
    private Integer age;
    private String lastname;

    //default Constructor
    public Person(){

    }

    public Person(String local_firstname, String local_lastname, Integer local_age){
        firstname = local_firstname;
        lastname = local_lastname;
        age = local_age;
    }



}
