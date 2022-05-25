package nl.kelsinga.bmpserver.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId


@Serializable
data class Folder(
    @Contextual val _id: Id<Folder> = newId(),
    val title: String = "",
    val tasks: List<Task> = emptyList(),
    val taskList: List<TaskList> = emptyList(),
    val folders: List<Folder> = emptyList()
)
