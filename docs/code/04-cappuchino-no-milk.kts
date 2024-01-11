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

val pancakesAndNonsense = arrayOf(
    Food.PANCAKES,

    Drink(
        beverage = Beverage.CAPPUCHINO,
        milk = false,
        sugar = false
    )
)
