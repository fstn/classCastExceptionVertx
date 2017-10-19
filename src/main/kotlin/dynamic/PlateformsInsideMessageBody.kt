package fstn.vertxFlow.core.dynamic

import fstn.vertxFlow.core.context.JsonDelegate
import kotlin.reflect.KMutableProperty1

/**
 * Represents user list of plateform
 * @author fstn
 */
class PlateformsInsideMessageBody : DynamicObject() {
    override fun getFields(): List<KMutableProperty1<out DynamicObject, out DynamicObject?>> {
        return listOf(PlateformsInsideMessageBody::facebook)
    }

    var facebook: FBUserPartInsideMessageBody? by JsonDelegate()
}