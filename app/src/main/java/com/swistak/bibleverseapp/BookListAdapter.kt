package com.swistak.bibleverseapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.adapter_view_layout.view.*

class BookListAdapter(context : Context, resource : Int, objects : Array<String>) : ArrayAdapter<String>(context,resource,objects )  {

    val res = resource
    private val con = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val bookName = getItem(position)

        val inflater = LayoutInflater.from(con)
        val convertView2 = inflater.inflate(res, parent,false)

        val bookNameTV = convertView2.ItemText



        bookNameTV.text = bookName


        return convertView2
    }
}