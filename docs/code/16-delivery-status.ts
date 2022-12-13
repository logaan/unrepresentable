type delivery_status
    = {state:     "preparing"}

    | {state:     "in_transit",
       shipped:   Date}

    | {state:     "lost_during_delivery",
       shipped:   Date}

    | {state:     "delivered",
       shipped:   Date,
       delivered: Date}

    | {state:     "returned",
       reason:    "dead_on_arrival" | "change_of_mind"
       shipped:   Date,
       delivered: Date,
       returned:  Date};
