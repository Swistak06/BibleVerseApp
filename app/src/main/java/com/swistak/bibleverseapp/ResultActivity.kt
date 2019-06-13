package com.swistak.bibleverseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_result.*
import okhttp3.*
import java.io.IOException

class ResultActivity : AppCompatActivity() {


    val gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val extras = intent.extras
        var resp : VerseResponse
        if (extras != null) {
            val value = extras.getString("ApiResponse")
            resp = gson.fromJson(value, VerseResponse::class.java)
            ResultTV.text = resp.text
            ResultTV.movementMethod = ScrollingMovementMethod()
        }

    }


}
