package com.swistak.bibleverseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_form.*
import android.text.SpannableStringBuilder
import android.text.Editable



class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("bookName")
            val editable = SpannableStringBuilder(value)
            BookNameInput.text = editable
        }



        searchBtn.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }


}
