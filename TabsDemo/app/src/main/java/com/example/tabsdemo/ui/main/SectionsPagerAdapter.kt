package com.example.tabsdemo.ui.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tabsdemo.R
import com.example.tabsdemo.WeatherFragment


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // TODO: создать фрагмент со сведениями о погоде
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return WeatherFragment.newInstance(context.resources.getStringArray(R.array.cities)[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // TODO: задать заголовок - название города
        return context.resources.getStringArray(R.array.cities)[position]
    }

    override fun getCount(): Int {
        // TODO: возвращать число доступных городов
        return context.resources.getStringArray(R.array.cities).size
    }
}