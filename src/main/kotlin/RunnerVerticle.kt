package fstn.vertxFlow.runner.application.verticle

import fstn.vertxFlow.core.dynamic.MessageBody
import fstn.vertxFlow.core.vertx.helper.RouterFactory
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.handler.sockjs.PermittedOptions
import io.vertx.rxjava.core.AbstractVerticle
import io.vertx.rxjava.core.Vertx
import io.vertx.rxjava.core.eventbus.Message

/**
 * @author fstn
 */
class RunnerVerticle : AbstractVerticle() {

    override fun start() {

        val eventBus = vertx.eventBus()

        RouterFactory.init(vertx,
                listOf(
        ))

        val router = RouterFactory.router

        RouterFactory.init(vertx,
                listOf(
                ))

        val toSend = JsonObject()
                .put("eventName", "EVENT_NAME")
                .put("context", JsonObject().put("input", JsonObject().put("txt", "txt")))

        eventBus.consumer<JsonObject>("SIMULATE_EVENT", EventSimulatortHandler(vertx))
        eventBus.consumer<MessageBody>("EVENT_NAME", FBHandler())
        eventBus.rxSend<JsonObject>("SIMULATE_EVENT", toSend)
                .toObservable()
                .subscribe(
                )
    }
}

class MissingEventNameException(body: JsonObject?) : RuntimeException(body?.encode())

class EventSimulatortHandler(val vertx: Vertx) : Handler<Message<JsonObject>> {
    override fun handle(message: Message<JsonObject>) {
        val eventName = message.body().getString("eventName") ?: throw MissingEventNameException(message.body())
        val context: MessageBody = MessageBody()
        context.update(message.body().getJsonObject("context"))
        vertx.eventBus().rxSend<MessageBody>(eventName, context)
                .toObservable()
                .subscribe(
                        { reply ->
                            message.reply(reply.body())
                        })
    }
}

class FBHandler : Handler<Message<MessageBody>> {
    override fun handle(message: Message<MessageBody>) {
        print(message.body() as MessageBody)
    }
}

