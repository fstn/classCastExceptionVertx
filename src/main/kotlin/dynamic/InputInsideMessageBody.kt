package fstn.vertxFlow.core.dynamic

import fstn.vertxFlow.core.context.JsonDelegate
import kotlin.reflect.KMutableProperty1

/**
 * user input from bot
 * @author fstn
 */
class InputInsideMessageBody : DynamicObject() {

    var txt:String? by JsonDelegate()
    var payload:String? by JsonDelegate()

    override fun getFields(): List<KMutableProperty1<out DynamicObject, out DynamicObject?>> {
        return listOf()
    }
}