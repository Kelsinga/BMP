package nl.kelsinga.bmpserver.database

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import nl.kelsinga.bmpserver.model.Folder
import org.litote.kmongo.coroutine.updateOne

class FolderDao(databaseString: String) : AbstractDao<Folder>(databaseString) {

    private val folderCollection = database.getCollection<Folder>()

    override suspend fun create(t: Folder) : UpdateResult?  = folderCollection.save(t)

    override suspend fun read(t: Any): Folder? = folderCollection.findOneById(t)

    override suspend fun update(t: Folder) : UpdateResult? = folderCollection.updateOne(t)

    override suspend fun delete(t: Any): DeleteResult? = folderCollection.deleteOneById(t)

    override suspend fun readAll(): List<Folder> = folderCollection.find().toList();

}
