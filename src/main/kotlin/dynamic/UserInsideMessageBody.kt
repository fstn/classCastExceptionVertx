package fstn.vertxFlow.core.dynamic

import fstn.vertxFlow.core.context.JsonDelegate
import kotlin.reflect.KMutableProperty1


class UserInsideMessageBody : DynamicObject() {
    override fun getFields(): List<KMutableProperty1<out DynamicObject, out DynamicObject?>> {
        return listOf(UserInsideMessageBody::plateforms)
    }

    var firstName by JsonDelegate<String>()
    var lastName by JsonDelegate<String>()
    var gender by JsonDelegate<String>()
    var type by JsonDelegate<String>()
    var picture by JsonDelegate<String>()
    var locale by JsonDelegate<String>()
    var heart by JsonDelegate<String>()
    var level by JsonDelegate<String>()
    var timezone by JsonDelegate<String>()
    var plateforms: PlateformsInsideMessageBody? by JsonDelegate()
}

enum class UserGender {
    MALE, FEMALE
}

enum class UserType {
    STUDENT
}
