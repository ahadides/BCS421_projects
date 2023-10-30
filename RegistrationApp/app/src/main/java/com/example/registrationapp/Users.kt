import java.io.Serializable

class Users(
    firstName: String,
    lastName: String,
    dateOfBirth: String,
    email: String,
    password: String,
    moneyEarned: Int, // New field for money earned
    questionCorrect: Int // New field for the number of correct questions
) : Serializable {
    var firstName: String = firstName
        set(value) {
            field = value
        }
        get() = field

    var lastName: String = lastName
        set(value) {
            field = value
        }
        get() = field

    var dateOfBirth: String = dateOfBirth
        set(value) {
            field = value
        }
        get() = field

    var email: String = email
        set(value) {
            field = value
        }
        get() = field

    var password: String = password
        set(value) {
            field = value
        }
        get() = field

    var moneyEarned: Int = moneyEarned
        set(value) {
            field = value
        }
        get() = field

    var questionCorrect: Int = questionCorrect
        set(value) {
            field = value
        }
        get() = field
}
