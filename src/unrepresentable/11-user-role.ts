var user_role: string;

user_role = "admin";               // This is fine
user_role = "deep_fried_football"; // This is nonsense

var user_role: "end_user" | "agent" | "admin";

user_role = "admin";               // This is fine
user_role = "deep_fried_football"; // This won't compile
