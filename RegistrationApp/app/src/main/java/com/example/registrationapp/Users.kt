package com.example.registrationapp

import java.io.Serializable

class Users(
    firstName: String,
    familyName: String,
    dateOfBirth: String,
    email: String,
    password: String
) : Serializable {
    var firstName: String = ""
        set(value) {
            field = value
        }
        get() = field

    var lastName: String = ""
        set(value) {
            field = value
        }
        get() = field

    var dateOfBirth: String = ""
        set(value) {
            field = value
        }
        get() = field

    var email: String = ""
        set(value) {
            field = value
        }
        get() = field

    var password: String = ""
        set(value) {
            field = value
        }
        get() = field

}