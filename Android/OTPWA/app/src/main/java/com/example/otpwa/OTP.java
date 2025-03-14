package com.example.otpwa;

import java.util.Date;

public class OTP {
//    private String id;
//    private String number;
//    private String otp;
//    private Date exp;

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public OTP(boolean status) {
        this.status = status;
    }
}
