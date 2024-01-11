import java.util.Date;

data class Parcel(val id                  : Int,      //   1
                  val state               : String,   // * 6
                  val shipped             : Date?,    // * 2
                  val lostDuringDelivery  : Boolean?, // * 3
                  val delivered           : Date?,    // * 2
                  val returned            : Date?,    // * 2
                  val deadOnArrivalReturn : Boolean?, // * 3
                  val changeOfMindReturn  : Boolean?) // * 3
                                                      // = 1080
