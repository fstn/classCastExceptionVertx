package fstn.vertxFlow.core.dynamic

import fstn.vertxFlow.core.context.JsonDelegate
import kotlin.reflect.KMutableProperty1

/**
 * FlowInsideMessageBody part of context
 * @author fstn
 */
class FlowInsideMessageBody : DynamicObject() {

    var id by JsonDelegate<String>()
    var name by JsonDelegate<String>()

    override fun getFields(): List<KMutableProperty1<out DynamicObject, out DynamicObject?>> {
        return listOf()
    }

    var facebook: FBUserPartInsideMessageBody? by JsonDelegate()
}