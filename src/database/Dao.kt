package nl.kelsinga.bmpserver.database

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult

interface Dao<T> {

    suspend fun create(t : T) : UpdateResult?

    suspend fun read(t : Any) : T?

    suspend fun readAll() : List<T>

    suspend fun update(t : T) : UpdateResult?

    suspend fun delete(t : Any) : DeleteResult?
}
