import java.util.Date;

data class Deletion(val deleted   : Date,
                    val deletedBy : String)

data class Record(val id       : Int,
                  val deletion : Deletion?)

val record = Record(id       = 1,
                    deletion = null)
