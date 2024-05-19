package com.example.loginmvvm.core.log

import android.util.Log

class LoggerImpl : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}