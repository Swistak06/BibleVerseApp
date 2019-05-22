package com.swistak.bibleverseapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private val TAB_TITLES = arrayOf(
    R.string.old_testament,
    R.string.new_testament
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private lateinit var oldTestamentBooks: OldTestamentBooks
    private lateinit var newTestamentBooks: NewTestamentBooks

    override fun getItem(position: Int): Fragment {
        return if (position == 1) newTestamentBooks
        else oldTestamentBooks
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
    fun setFragments(oldTestamentBooks: OldTestamentBooks, newTestamentBooks: NewTestamentBooks){
        this.oldTestamentBooks = oldTestamentBooks
        this.newTestamentBooks = newTestamentBooks
    }
}