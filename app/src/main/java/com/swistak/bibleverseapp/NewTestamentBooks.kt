package com.swistak.bibleverseapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_new_testament_books.view.*


class NewTestamentBooks : Fragment() {


    var booksName = arrayOf("Matthew","Mark","Luke","John","Acts","Romans","1 Corinthians","2 Corinthians","Galatians","Ephesians","Philippians",
        "Colossians","1 Thessalonians", "2 Thessalonians","1 Timothy","2 Timothy","Titus","Philemon", "Hebrews","James","1 Peter","2 Peter",
        "1 John","2 John","3 John","Jude","Revelation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_testament_books, container, false)
        var arrayAdapter = BookListAdapter(context!!,R.layout.adapter_view_layout, booksName )
        view.newBooksList.adapter = arrayAdapter

        view.newBooksList.setOnItemClickListener{
            parent, view, position, id ->
        }

        view.newBooksList.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(context, FormActivity::class.java)
            intent.putExtra("bookName", arrayAdapter.getItem(position))
            startActivity(intent)
        }

        view.newBooksSearchFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                arrayAdapter.filter.filter(p0)
            }
        })
        return view
    }
}
