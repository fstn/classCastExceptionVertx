package fstn.vertxFlow.core.vertx.helper

import io.vertx.core.Handler
import io.vertx.ext.web.handler.sockjs.BridgeOptions
import io.vertx.ext.web.handler.sockjs.PermittedOptions
import io.vertx.rxjava.core.Vertx
import io.vertx.rxjava.core.http.HttpServerRequest
import io.vertx.rxjava.ext.web.Router
import io.vertx.rxjava.ext.web.handler.sockjs.SockJSHandler

/**
 *
 * @author fstn
 */
object RouterFactory {
    lateinit var router:Router
    lateinit var sockJSHandler:SockJSHandler
    fun init(vertx: Vertx, permittedOptions: List<PermittedOptions>) {
        router = Router.router(vertx)

        router.route().handler(io.vertx.rxjava.ext.web.handler.CorsHandler.create("*")
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
                .allowedHeader("Access-Control-Request-Method")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Headers")
                .allowedHeader("Content-Type"));

        // Allow events for the designated addresses in/out of the event bus bridge
        val opts = BridgeOptions()
                .setPingTimeout(Long.MAX_VALUE)

        permittedOptions.forEach({opts.addInboundPermitted(it)})
        permittedOptions.forEach({opts.addOutboundPermitted(it)})


        sockJSHandler = SockJSHandler.create(vertx).bridge(opts)
        router.route("/eventbus/*").handler(sockJSHandler)

        // Start the web server and tell it to use the router to handle requests.
        vertx.createHttpServer().requestHandler(Handler<HttpServerRequest> { router.accept(it) }).listen(8080)
    }
}