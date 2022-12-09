type food = "pancakes";

type drink
    = {beverage: "batch_brew", milk: boolean, sugar: boolean}
    | {beverage: "espresso",   milk: boolean, sugar: boolean}
    | {beverage: "cappuchino",                sugar: boolean}
    | {beverage: "tea",        milk: boolean, sugar: boolean};

type order_item = food | drink;
type order      = order_item[];
