import java.util.Date;

sealed interface DeliveryStatus

class Preparing(): DeliveryStatus

data class InTransit(val shipped: Date): DeliveryStatus

data class LostDuringDelivery(val shipped: Date): DeliveryStatus

data class Delivered(
    val shipped: Date,
    val delivered: Date
): DeliveryStatus

enum class ReturnReason {DEAD_ON_ARRIVAL, CHANGE_OF_MIND}
data class Returned(val reason: ReturnReason,
                    val shipped: Date,
                    val delivered: Date,
                    val returned: Date
): DeliveryStatus
