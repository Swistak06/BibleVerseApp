package com.swistak.bibleverseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_result.*
import okhttp3.*
import java.io.IOException

class ResultActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("ApiResponse")
            ResultTV.text = value
        }

    }


}
