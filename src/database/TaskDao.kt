package nl.kelsinga.bmpserver.database

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import nl.kelsinga.bmpserver.model.SubTask
import nl.kelsinga.bmpserver.model.Task
import org.litote.kmongo.coroutine.updateOne

class TaskDao(databaseString: String) : AbstractDao<Task>(databaseString){

    private val taskCollection = database.getCollection<Task>()

    override suspend fun create(t: Task) : UpdateResult? = taskCollection.save(t)

    override suspend fun read(t: Any): Task?  = taskCollection.findOneById(t)

    override suspend fun delete(t: Any) : DeleteResult? = taskCollection.deleteOneById(t)

    /*
    Null properties are taken into account during the update (they are set to null in the document in MongoDb).
    If you prefer to ignore null properties during the update, you can use the updateOnlyNotNullProperties parameter:
    col.updateOne(newFriend, updateOnlyNotNullProperties = true)
    If you think it should be the default behaviour for all updates:
    UpdateConfiguration.updateOnlyNotNullProperties = true
     */
    override suspend fun update(t: Task) : UpdateResult? = taskCollection.updateOne(t)

    override suspend fun readAll(): List<Task> = taskCollection.find().toList();
}
