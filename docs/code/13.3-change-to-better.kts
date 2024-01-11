import java.util.Date;

data class Record(val id         : Int,
                  val deleted    : Date?,
                  val deleted_by : String?)

data class Deletion(val deleted    : Date,
                    val deleted_by : String)

data class NewRecord(val id       : Int,
                     val deletion : Deletion?)
