var user_role: string;

user_role = "admin";                // This is fine
user_role = "milk_free_cappuccino"; // This is nonsense

var user_role: "end_user" | "agent" | "admin";

user_role = "admin";                // This is fine
user_role = "milk_free_cappuccino"; // This won't compile
