package com.example.pecode

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class NumberAdapter(fragment:FragmentActivity): FragmentStateAdapter(fragment) {
    var list = mutableListOf<Int>()
    override fun getItemCount(): Int {
        Log.d("List",list.size.toString())
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
      val fragment = NumberFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJ,position+1)
        }
        return fragment
    }
}