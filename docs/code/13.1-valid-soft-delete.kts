import java.util.Date;

data class Record(val id        : Int,
                  val deleted   : Date?,
                  val deletedBy : String?)

val record = Record(id        = 1,
                    deleted   = Date(1705014201),
                    deletedBy = "Logan")
