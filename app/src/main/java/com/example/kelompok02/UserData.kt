package com.example.kelompok02

object UserData {
    var firstName: String = ""
    var lastName: String = ""
    var username: String = ""
    var email: String = ""
    var password: String = ""
    var phoneNumber: String = ""
    var dateOfBirth: String = ""
    var address: String = ""
    
    // Avatar Customization States
    var hairEnabled: Boolean = true
    var shirtEnabled: Boolean = true
    var pantsEnabled: Boolean = true
    var shoesEnabled: Boolean = true

    fun isRegistered(): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }
    fun validateLogin(inputUsername: String, inputPassword: String): Boolean {
        return isRegistered() &&
                inputUsername == username &&
                inputPassword == password
    }
}