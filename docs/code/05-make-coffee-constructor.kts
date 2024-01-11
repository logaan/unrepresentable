enum class Food {
    PANCAKES
}

enum class Beverage {
    TEA,
    ESPRESSO,
    CAPPUCHINO,
    BATCH_BREW
}

data class Drink(
    val beverage: Beverage,
    val milk:     Boolean,
    val sugar:    Boolean
)

fun makeCoffee(beverage: Beverage,
               milk: Boolean,
               sugar: Boolean): Drink {

    if(beverage == Beverage.CAPPUCHINO && !milk) {
        throw IllegalArgumentException("Nonsense")
    } else {
        return Drink(
            beverage = beverage,
            milk = milk,
            sugar = sugar
        )
    }

}
