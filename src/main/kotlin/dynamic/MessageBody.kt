package fstn.vertxFlow.core.dynamic

import fstn.vertxFlow.core.context.JsonDelegate
import kotlin.reflect.KMutableProperty1


/**
 * MessageBody
 */
class MessageBody : DynamicObject() {
    override fun getFields(): List<KMutableProperty1<out DynamicObject, out DynamicObject>> {
       return listOf(MessageBody::user,MessageBody::flow,MessageBody::input)
    }

    var user: UserInsideMessageBody by JsonDelegate()
    var flow: FlowInsideMessageBody by JsonDelegate()
    var input: InputInsideMessageBody by JsonDelegate()
}
