package nl.kelsinga.bmpserver.database

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import nl.kelsinga.bmpserver.model.Folder
import nl.kelsinga.bmpserver.model.Task
import nl.kelsinga.bmpserver.model.TaskList
import org.litote.kmongo.coroutine.updateOne
import org.litote.kmongo.eq

class TaskListDao (databaseString: String) : AbstractDao<TaskList>(databaseString) {

    private val taskListCollection = database.getCollection<TaskList>()

    override suspend fun create(t: TaskList) : UpdateResult? = taskListCollection.save(t)

    override suspend fun read(t: Any): TaskList? = taskListCollection.findOne(Folder::title eq t)

    override suspend fun update(t: TaskList) : UpdateResult? = taskListCollection.updateOne(t)

    override suspend fun delete(t: Any): DeleteResult? = taskListCollection.deleteOneById(t)

    override suspend fun readAll(): List<TaskList> = taskListCollection.find().toList();
}
