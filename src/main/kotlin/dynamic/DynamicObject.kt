package fstn.vertxFlow.core.dynamic

import io.vertx.core.json.JsonObject
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.createInstance

/**
 * Allows to have a dynamic par on object
 * @author fstn
 */
abstract class DynamicObject : JsonObject() {
    fun update(jsonObject: JsonObject): DynamicObject {
        jsonObject.fieldNames().forEach { fieldName: String ->
            val field = getFields().singleOrNull { it.name == fieldName }
            if (field != null) {
                val currentValue = this.getJsonObject(fieldName) ?:    (field.returnType.classifier as KClass<DynamicObject>).createInstance()
                (currentValue as DynamicObject).update(jsonObject.getJsonObject(fieldName))
                field.setter.call(this,currentValue)
            } else {
                this.put(fieldName, jsonObject.getValue(fieldName))
            }
        }
        return this
    }

    abstract fun getFields(): List<KMutableProperty1<out DynamicObject,out DynamicObject?>>
}