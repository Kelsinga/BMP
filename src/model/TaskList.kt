package nl.kelsinga.bmpserver.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class TaskList(
    @Contextual val _id: Id<TaskList> = newId(),
    val title: String = "",
    val tasks: List<Task> = emptyList()
)
