@file:OptIn(KtorExperimentalLocationsAPI::class)

package nl.kelsinga.bmpserver

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import nl.kelsinga.bmpserver.database.*
import nl.kelsinga.bmpserver.ktormodules.folders
import nl.kelsinga.bmpserver.ktormodules.subtasks
import nl.kelsinga.bmpserver.ktormodules.tasklists
import nl.kelsinga.bmpserver.ktormodules.tasks
import nl.kelsinga.bmpserver.model.Folder
import nl.kelsinga.bmpserver.model.SubTask
import nl.kelsinga.bmpserver.model.Task
import nl.kelsinga.bmpserver.model.TaskList
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton
import org.litote.kmongo.id.serialization.IdKotlinXSerializationModule

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@OptIn(KtorExperimentalLocationsAPI::class)
@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.mainModule(testing: Boolean = false) {
    install(DataConversion)
    // Allows to use classes annotated with @Location to represent URLs.
    // They are typed, can be constructed to generate URLs, and can be used to register routes.
    install(Locations) {}
    // This adds automatically Date and Server headers to each response, and would allow you to configure
    // additional headers served to each response.
    install(DefaultHeaders) {}
    // This uses use the logger to log every call (request/response)
    install(CallLogging)

    // Based on the Accept header, allows to reply with arbitrary objects converting them into JSON
    // when the client accepts it. using Kotlinx.Serialization
    install(ContentNegotiation) {
        json(
            Json { serializersModule = IdKotlinXSerializationModule },
            contentType = ContentType.Application.Json
        )

    }

    di {
        bind<Dao<Folder>>() with singleton { FolderDao("bmp") }
        bind<Dao<Task>>() with singleton { TaskDao("bmp") }
        bind<Dao<TaskList>>() with singleton { TaskListDao("bmp") }
        bind<Dao<SubTask>>() with singleton { SubTaskDao("bmp") }
    }


    // We will register all the available routes here
    routing {
        index()
        tasks()
        folders()
        subtasks()
        tasklists()

        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }
        }

    }

}

@Location("/")
class Index()

@KtorExperimentalLocationsAPI
fun Routing.index() {
    get<Index> {
        call.respondText("root", contentType = ContentType.Text.Plain)
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

