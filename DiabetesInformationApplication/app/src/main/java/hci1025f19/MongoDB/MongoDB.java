package hci1025f19.MongoDB;

import android.os.AsyncTask;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import hci1025f19.database.ErrorObject;

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
        document.put("page", e.getPage());
        document.put("date", e.getDate());
        document.put("readableDate", e.getReadableDate());
        document.put("type", e.getType());
        document.put("element", e.getElement());
        document.put("task", e.getTask());
        document.put("frequency", e.getFrequency());
        document.put("completed", e.getCompleted());
        document.put("erroreffect", e.getError());
        document.put("statisfaction", e.getEffect());
        document.put("id", e.getId());
        document.put("advanced", e.getAdvanced());
        document.put("expectation",e.getExpectation());
        document.put("hotfixes",e.getHotfixes());
        collection.insertOne(document);

        //Might have to extend this with a wait
        mongoClient.close();


        return true;
    }


}
