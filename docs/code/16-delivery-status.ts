type delivery_status
    = {type: "preparing"}
    | {type: "in_transit",
       shipped: Date}
    | {type: "delivered",
       shipped: Date, delivered: Date}
    | {type: "lost_during_delivery",
       shipped: Date}
    | {type: "dead_on_arrival_return",
       shipped: Date, delivered: Date, returned: Date}
    | {type: "change_of_mind_return",
       shipped: Date, delivered: Date, returned: Date};

var parcel: {id:              number,
             product_id:      number,
             delivery_status: delivery_status};
