
import fstn.vertxFlow.runner.application.verticle.RunnerVerticle
import io.vertx.rxjava.core.RxHelper
import io.vertx.rxjava.core.Vertx

fun main(args : Array<String>) {
    val vertx = Vertx.vertx()
                RxHelper.deployVerticle(vertx, RunnerVerticle()).subscribe()
}