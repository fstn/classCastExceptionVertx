package fstn.vertxFlow.core.context

import io.vertx.core.json.JsonObject
import kotlin.reflect.KProperty

/**
 * Delegate that allow to map property to json one
 */
class JsonDelegate<T> {
    operator fun getValue(thisRef: JsonObject?, property: KProperty<*>): T {
        return thisRef!!.getValue(property.name) as T
    }

    operator fun setValue(thisRef: JsonObject?, property: KProperty<*>, value: T) {
        thisRef!!.put(property.name, value)
    }
}
