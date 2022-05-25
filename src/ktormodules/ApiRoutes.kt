@file:OptIn(KtorExperimentalLocationsAPI::class)

package nl.kelsinga.bmpserver.ktormodules

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import nl.kelsinga.bmpserver.database.Dao
import nl.kelsinga.bmpserver.model.Folder
import nl.kelsinga.bmpserver.model.SubTask
import nl.kelsinga.bmpserver.model.Task
import nl.kelsinga.bmpserver.model.TaskList
import org.bson.types.ObjectId
import org.kodein.di.instance
import org.kodein.di.ktor.di


@KtorExperimentalLocationsAPI
fun Route.tasks() {
    val taskDao by di().instance<Dao<Task>>()
    route("/tasks/") {
        get{
            val tasks = taskDao.readAll()
            call.respond(tasks)
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toString()
            val foundTask = taskDao.read(ObjectId(id))
            if (foundTask != null) {
                call.respond(foundTask)
            } else {
                call.respond("didn't find anything")
            }
        }

        post<Task> {
            val task = call.receive<Task>()
            val updateResult = taskDao.create(task)
            val success = updateResult?.wasAcknowledged()
            if(success != null && success){
                val taskId = updateResult.upsertedId?.asObjectId()?.value?.toString() ?: "error"
                call.respond(HttpStatusCode.OK, taskId)
            } else {
                call.respond(HttpStatusCode.ExpectationFailed)
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]!!.toString()
            val success = taskDao.delete(ObjectId(id))?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

        patch<Task> {
            val task = call.receive<Task>()
            val success = taskDao.update(task)?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

    }
}

@KtorExperimentalLocationsAPI
fun Route.folders() {
    val folderDao by di().instance<Dao<Folder>>()
    route("/folders/") {
        get{
            val folders = folderDao.readAll()
            call.respond(folders)
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toString()
            val foundFolder = folderDao.read(ObjectId(id))
            if (foundFolder != null) {
                call.respond(foundFolder)
            } else {
                call.respond("didn't find anything")
            }
        }

        post<Folder> {
            val folder = call.receive<Folder>()
            val updateResult = folderDao.create(folder)
            val success = updateResult?.wasAcknowledged()
            if(success != null && success){
                val folderId = updateResult.upsertedId?.asObjectId()?.value?.toString() ?: "error"
                call.respond(HttpStatusCode.OK, folderId)
            } else {
                call.respond(HttpStatusCode.ExpectationFailed)
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]!!.toString()
            val success = folderDao.delete(ObjectId(id))?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

        patch<Folder> {
            val folder = call.receive<Folder>()
            val success = folderDao.update(folder)?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

    }
}


@KtorExperimentalLocationsAPI
fun Route.subtasks() {

    val dao by di().instance<Dao<SubTask>>()
    route("/subtasks/") {
        get{
            val subtasks = dao.readAll()
            call.respond(subtasks)
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toString()
            val foundItem = dao.read(ObjectId(id))
            if (foundItem != null) {
                call.respond(foundItem)
            } else {
                call.respond("didn't find anything")
            }
        }

        post<SubTask> {
            val folder = call.receive<SubTask>()
            val updateResult = dao.create(folder)
            val success = updateResult?.wasAcknowledged()
            if(success != null && success){
                val folderId = updateResult.upsertedId?.asObjectId()?.value?.toString() ?: "error"
                call.respond(HttpStatusCode.OK, folderId)
            } else {
                call.respond(HttpStatusCode.ExpectationFailed)
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]!!.toString()
            val success = dao.delete(ObjectId(id))?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

        patch<SubTask> {
            val folder = call.receive<SubTask>()
            val success = dao.update(folder)?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

    }
}


@KtorExperimentalLocationsAPI
fun Route.tasklists() {
    val dao by di().instance<Dao<TaskList>>()
    route("/tasklists/") {
        get{
            val tasklists = dao.readAll()
            call.respond(tasklists)
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toString()
            val foundItem = dao.read(ObjectId(id))
            if (foundItem != null) {
                call.respond(foundItem)
            } else {
                call.respond("didn't find anything")
            }
        }

        post<TaskList> {
            val taskLists = call.receive<TaskList>()
            val updateResult = dao.create(taskLists)
            val success = updateResult?.wasAcknowledged()
            if(success != null && success){
                val folderId = updateResult.upsertedId?.asObjectId()?.value?.toString() ?: "error"
                call.respond(HttpStatusCode.OK, folderId)
            } else {
                call.respond(HttpStatusCode.ExpectationFailed)
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]!!.toString()
            val success = dao.delete(ObjectId(id))?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

        patch<TaskList> {
            val taskLists = call.receive<TaskList>()
            val success = dao.update(taskLists)?.wasAcknowledged()
            val httpStatusCode = if(success != null) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
            call.respond(httpStatusCode)
        }

    }
}



