package hci923e18.MongoDB;

import android.os.AsyncTask;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.logging.Level;
import java.util.logging.Logger;

import hci923e18.database.ErrorObject;

public class MongoDB extends AsyncTask<ErrorObject, Void, Boolean> {

    @Override
    protected Boolean doInBackground(ErrorObject... errorObjects) {
        ErrorObject e = errorObjects[0];

        MongoClientURI uri = new MongoClientURI(
                "mongodb://Mobileapplication:d3swUuerAedKlera@diabetesapplication-shard-00-00-j8soa.mongodb.net:27017,diabetesapplication-shard-00-01-j8soa.mongodb.net:27017,diabetesapplication-shard-00-02-j8soa.mongodb.net:27017/test?ssl=true&replicaSet=DIAbetesApplication-shard-0&authSource=admin&retryWrites=true");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ReportDatabase");

        MongoCollection<BasicDBObject> collection = database.getCollection("Reports", BasicDBObject.class);

        BasicDBObject document = new BasicDBObject();
        document.put("name", e.getName());
        document.put("message", e.getMessage());
        collection.insertOne(document);

        //Might have to extend this with a wait
        mongoClient.close();


        return true;
    }


}
