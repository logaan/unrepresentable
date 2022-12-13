type food = "pancakes";

type beverage
    = "tea"
    | "espresso"
    | "cappuchino"
    | "batch_brew";

type drink =
    {beverage: beverage,
     milk:     boolean,
     sugar:    boolean};
