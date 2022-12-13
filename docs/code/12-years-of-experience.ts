var years_of_experience: number;

years_of_experience = 5;    // This is fine
years_of_experience = 1.5;  // Seems reasonable

years_of_experience = 1000; // Nonsense
years_of_experience = -42;  // Negative nonsense

var years_of_experience:
    0 | 1 | 2 | 3 | 4 | 5 |
    6 | 7 | 8 | 9 | "10+";

years_of_experience = 5;    // This is fine

years_of_experience = 1.5;  // Nonsense
years_of_experience = 1000; // won't
years_of_experience = -42;  // compile
