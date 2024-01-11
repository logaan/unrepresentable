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
