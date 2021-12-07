package com.inaki.starwarsmvpapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.inaki.starwarsmvpapp.views.CharactersFragment
import com.inaki.starwarsmvpapp.views.PlanetsFragment
import com.inaki.starwarsmvpapp.views.StarshipsFragment

/**
 * This is the adapter to switch between fragment in the view pager
 *
 * @param fragmentManager We need to pass supportFragmentManager from the activity
 * @param lifecycle We need to pass the activity lifecycle to create the fragments
 */
class FragmentsAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    // this method is the count of fragment we want to display
    override fun getItemCount(): Int = 3

    // this is the creation of the fragments depending on the position
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CharactersFragment.newInstance()
            1 -> PlanetsFragment.newInstance()
            2 -> StarshipsFragment.newInstance()
            else -> CharactersFragment.newInstance()
        }
    }
}