package nl.kelsinga.bmpserver.database

import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClients
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

abstract class AbstractDao<T>(databaseString: String) : Dao<T>  {
    private val connectionString: ConnectionString = ConnectionString("mongodb://kris.elsinga:3VCef6sqLXRZZ2tHLv2r@giblet.eu:27017")
    private val client = KMongo.createClient(connectionString).coroutine
    protected val database = client.getDatabase(databaseString).

    init {
        println("Inits")
    }
}
