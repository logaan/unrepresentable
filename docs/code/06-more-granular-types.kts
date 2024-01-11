enum class Food {
    PANCAKES
}

sealed interface Drink

data class Tea(val milk: Boolean, val sugar: Boolean)
data class Espresso(val milk: Boolean, val sugar: Boolean)
data class Cappuchino(val sugar: Boolean)
data class BatchBrew(val milk: Boolean, val sugar: Boolean)
