function makeCoffee(beverage: beverage,
                    milk:     boolean,
                    sugar:    boolean) {

    if ( beverage == "cappuchino" && !milk ) {
        throw "Nonsense";
    } else {
        return {beverage: beverage,
                milk:     milk,
                sugar:    sugar};
    }

}
