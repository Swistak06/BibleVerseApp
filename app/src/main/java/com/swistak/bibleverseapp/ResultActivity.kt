package com.swistak.bibleverseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_result.*
import okhttp3.*
import java.io.IOException
import android.widget.Toast



class ResultActivity : AppCompatActivity() {


    val gson = Gson()
    var text = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val extras = intent.extras
        var resp : VerseResponse
        if (extras != null) {
            val value = extras.getString("ApiResponse")
            resp = gson.fromJson(value, VerseResponse::class.java)
            BookNameTV.text = resp.verses[0].book_name
            ChapterNumberTV.text = "Chapter " + resp.verses[0].chapter

            if(extras.getBoolean("combinedText")){
                resp.verses.forEach {
                    it.text = it.text.dropLast(2)
                    text += it.text
                }
            }
            else{
                resp.verses.forEach {
                    text = text + "Verse "+ it.verse + "\n" + it.text + "\n"
                }
            }
            ResultTV.text = text
            ResultTV.movementMethod = ScrollingMovementMethod()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }


}
