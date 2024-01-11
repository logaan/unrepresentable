import java.util.Date;

data class Parcel(val id                  : Int,
                  val state               : String,
                  val shipped             : Date?,
                  val lostDuringDelivery  : Boolean?,
                  val delivered           : Date?,
                  val returned            : Date?,
                  val deadOnArrivalReturn : Boolean?,
                  val changeOfMindReturn  : Boolean?)
