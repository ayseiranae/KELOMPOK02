package com.example.kelompok02

/**
 * Singleton object untuk menyimpan data registrasi user.
 * Digunakan sebagai pengganti database — data hanya bertahan selama aplikasi berjalan.
 */
object UserData {
    var firstName: String = ""
    var lastName: String = ""
    var username: String = ""
    var email: String = ""
    var password: String = ""
    var phoneNumber: String = ""
    var dateOfBirth: String = ""
    var address: String = ""

    /** Cek apakah user sudah registrasi (minimal email & password terisi) */
    fun isRegistered(): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }

    /** Validasi login: cocokkan email & password */
    fun validateLogin(inputEmail: String, inputPassword: String): Boolean {
        return isRegistered() &&
                inputEmail == email &&
                inputPassword == password
    }
}
