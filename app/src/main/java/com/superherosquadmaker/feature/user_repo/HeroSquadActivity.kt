package com.superherosquadmaker.feature.user_repo

import android.os.Bundle
import com.superherosquadmaker.R
import com.superherosquadmaker.feature.user_repo.listing.HeroesFragment
import dagger.android.support.DaggerAppCompatActivity

class HeroSquadActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.squad_maker_main_activity)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_host_id)

        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_host_id, HeroesFragment.newInstance(), FRAGMENT_TAG)
            }.commit()
        }
    }

    companion object {
        const val FRAGMENT_TAG = "current_fragment"
    }
}
