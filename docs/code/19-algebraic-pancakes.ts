// food = 1
type food = "pancakes";

// beverage = 1 + 1
// beverage = 2
type beverage
    = "tea"
    | "batch_brew";

// drink = beverage * milk * sugar
// drink = 2 * 2 * 2
// drink = 8
type drink =
    {beverage: beverage,
     milk:     boolean,
     sugar:    boolean};
