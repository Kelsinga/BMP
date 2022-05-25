package nl.kelsinga.bmpserver.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class Task(
    @Contextual val _id : Id<Task> = newId(),
    val title: String = "",
    val description: String = "",
    val subTasks: List<SubTask> = emptyList()
)
