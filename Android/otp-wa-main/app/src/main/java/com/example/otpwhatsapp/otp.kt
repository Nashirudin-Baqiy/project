package com.example.otpwhatsapp

import java.util.*

class otp {
    private lateinit var id: String
    private lateinit var number: String
    private lateinit var otp: String
    private lateinit var exp: Date

    constructor(id: String, number: String, otp: String, exp: Date) {
        this.id = id
        this.number = number
        this.otp = otp
        this.exp = exp
    }

    
}