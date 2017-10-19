package fstn.vertxFlow.core.dynamic

import fstn.vertxFlow.core.context.JsonDelegate
import kotlin.reflect.KMutableProperty1

/**
 * Facebook part of user declaration
 * @author fstn
 */
class FBUserPartInsideMessageBody : DynamicObject() {
    override fun getFields(): List<KMutableProperty1<out DynamicObject, out DynamicObject?>> {
        return listOf()
    }

    var id by JsonDelegate<String>()

}
