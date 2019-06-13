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
import kotlinx.android.synthetic.main.activity_result.*


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

            when {
                BeginVerseInput.text.isNullOrEmpty()-> displayErrorMsg("Enter begin verse range value!")
                BookNameInput.text.isNullOrEmpty() -> displayErrorMsg("Enter book name!")
                ChapterNumberInput.text.isNullOrEmpty() -> displayErrorMsg("Enter number of chapter!")
                !EndVerseInput.text.isNullOrEmpty() && Integer.parseInt(EndVerseInput.text.toString()) < Integer.parseInt(BeginVerseInput.text.toString()) -> displayErrorMsg("End verse range value cannot be lower than begin range!")
                else -> {
                    val intent1 = Intent(this, ResultActivity::class.java)
                    intent1.putExtra("bookName",BookNameInput.text.toString())
                    intent1.putExtra("chapterNum",ChapterNumberInput.text.toString())
                    intent1.putExtra("beginVerse",BeginVerseInput.text.toString())
                    if(!EndVerseInput.text.isNullOrEmpty())
                        intent1.putExtra("endVerse",ChapterNumberInput.text.toString())
                    else
                        intent1.putExtra("endVerse","0")
                    intent1.putExtra("combinedText",CombinedTextSwitch.isChecked)
                    startActivity(intent1)
                }
            }
        }
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

}
