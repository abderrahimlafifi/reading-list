package com.raydogs.app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raydogs.app.MainActivity
import com.raydogs.app.PostListFragment

class CategoryPagerAdapter(
    activity: FragmentActivity,
    private val tabs: List<MainActivity.Tab>
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = tabs.size

    override fun createFragment(position: Int): Fragment =
        PostListFragment.newInstance(tabs[position].categoryId)
}
