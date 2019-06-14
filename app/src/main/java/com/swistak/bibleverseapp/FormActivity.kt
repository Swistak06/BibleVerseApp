package com.swistak.bibleverseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_form.*
import android.text.SpannableStringBuilder
import android.text.Editable
import android.view.View
import androidx.core.os.HandlerCompat.postDelayed
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*
import okhttp3.*
import java.io.IOException


class FormActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private var resp = "sfsa"
    private var apiCallUrl = ""



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

            when {
                BookNameInput.text.isNullOrEmpty() -> displayErrorMsg("Enter book name!")
                ChapterNumberInput.text.isNullOrEmpty() -> displayErrorMsg("Enter number of chapter!")
                BeginVerseInput.text.isNullOrEmpty()-> displayErrorMsg("Enter begin verse range value!")
                !EndVerseInput.text.isNullOrEmpty() && Integer.parseInt(EndVerseInput.text.toString()) < Integer.parseInt(BeginVerseInput.text.toString()) -> displayErrorMsg("End verse range value cannot be lower than begin range!")
                else -> {
                    val intent1 = Intent(this, ResultActivity::class.java)
                    apiCallUrl = apiCallUrl + BookNameInput.text + ChapterNumberInput.text + ":" + BeginVerseInput.text
                    if(!EndVerseInput.text.isNullOrEmpty()){
                        apiCallUrl =  apiCallUrl + "-" + EndVerseInput.text
                    }
                    intent1.putExtra("combinedText",CombinedTextSwitch.isChecked)
                    run(apiCallUrl,intent1)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        apiCallUrl = "https://bible-api.com/"
    }

    private fun displayErrorMsg(message : String){
        ErrorLabel.text = message
        ErrorLabel.visibility = View.VISIBLE

        Handler().postDelayed(
            {
                ErrorLabel.visibility = View.INVISIBLE
            },
            3000
        )
    }




    fun run(url: String, intent1 : Intent) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    displayErrorMsg("Connection problem")
                }
            }
            override fun onResponse(call: Call, response: Response){
                resp = response.body()!!.string()
                if(resp  == "{\"error\":\"not found\"}"){
                    runOnUiThread { displayErrorMsg("Verses not found") }
                }
                else{
                    intent1.putExtra("ApiResponse",resp)
                    startActivity(intent1)
                }
            }
        })
    }

}
