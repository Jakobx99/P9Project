package hci923e18.MongoDB;

import android.os.AsyncTask;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import hci923e18.database.ErrorObject;
import hci923e18.database.UCIAdvancedObject;

public class MongoDBAdvanced  extends AsyncTask<UCIAdvancedObject, Void, Boolean> {

    @Override
    protected Boolean doInBackground(UCIAdvancedObject... uciAdvancedObjects) {
        UCIAdvancedObject e = uciAdvancedObjects[0];

        MongoClientURI uri = new MongoClientURI(
                "mongodb://Mobileapplication:d3swUuerAedKlera@diabetesapplication-shard-00-00-j8soa.mongodb.net:27017,diabetesapplication-shard-00-01-j8soa.mongodb.net:27017,diabetesapplication-shard-00-02-j8soa.mongodb.net:27017/test?ssl=true&replicaSet=DIAbetesApplication-shard-0&authSource=admin&retryWrites=true");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ReportDatabase");

        MongoCollection<BasicDBObject> collection = database.getCollection("Diary", BasicDBObject.class);

        BasicDBObject document = new BasicDBObject();
        document.put("diary", e.getDiary());
        document.put("good", e.getGood());
        document.put("bad", e.getBad());
        document.put("id", e.getId());
        document.put("date", e.getDate());
        document.put("readableDate", e.getReadableDate());

        collection.insertOne(document);

        //Might have to extend this with a wait
        mongoClient.close();


        return true;
    }

}
