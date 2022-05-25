package nl.kelsinga.bmpserver.database

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import nl.kelsinga.bmpserver.model.Folder
import nl.kelsinga.bmpserver.model.SubTask
import org.litote.kmongo.coroutine.updateOne
import org.litote.kmongo.eq

class SubTaskDao (databaseString: String) : AbstractDao<SubTask>(databaseString) {

    private val subTaskCollection = database.getCollection<SubTask>()

    override suspend fun create(t: SubTask) : UpdateResult? = subTaskCollection.save(t)

    override suspend fun read(t: Any): SubTask? = subTaskCollection.findOne(Folder::title eq t )

    override suspend fun update(t: SubTask) : UpdateResult? = subTaskCollection.updateOne(t)

    override suspend fun delete(t: Any): DeleteResult? = subTaskCollection.deleteOneById(t)

    override suspend fun readAll(): List<SubTask> = subTaskCollection.find().toList();

}
