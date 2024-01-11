import java.util.Date;

data class Record(val id         : Int,
                  val deleted    : Date?,
                  val deleted_by : String?)

val record = Record(id         = 1,
                    deleted    = null,
                    deleted_by = null)
