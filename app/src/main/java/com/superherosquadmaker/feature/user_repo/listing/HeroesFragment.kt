package com.superherosquadmaker.feature.user_repo.listing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.superherosquadmaker.R
import com.superherosquadmaker.core.ui.setDivider
import com.superherosquadmaker.di.IDaggerFactoryViewModel
import com.superherosquadmaker.feature.user_repo.HeroSquadActivity
import com.superherosquadmaker.feature.user_repo.repo_detail.HeroDetailFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_heroes.*
import javax.inject.Inject

class HeroesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: IDaggerFactoryViewModel

    private lateinit var viewController: HeroViewController
    private lateinit var adapter: HeroesAdapter
    private lateinit var squadAdapter: SquadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_heroes, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewController = ViewModelProvider(this, viewModelFactory).get(HeroViewController::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewController.onCreate()

        adapter = HeroesAdapter { id ->
            redirectToHeroDetailPage(id)
        }

        todoRecyclerview.adapter = adapter
        todoRecyclerview.layoutManager = LinearLayoutManager(context)
        todoRecyclerview.setDivider(R.drawable.list_divider)
        todoRecyclerview.isNestedScrollingEnabled = false
        todoRecyclerview.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))

        squadAdapter = SquadAdapter {
            redirectToHeroDetailPage(it)
        }
        squadRecyclerview.adapter = squadAdapter
        squadRecyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        setupObservers()
        viewController.onViewReady()
    }

    private fun setupObservers() {
        viewController.mutableHeroList.observe(this, Observer { listRepos ->
            adapter.addAll(listRepos)
        })

        viewController.mutableSquadList.observe(this, Observer { listRepos ->
            if (listRepos.isNullOrEmpty().not()) {
                squadGroup.visibility = View.VISIBLE
                squadAdapter.addAll(listRepos)
            } else {
                squadGroup.visibility = View.GONE
            }
        })
    }

    private fun redirectToHeroDetailPage(id: Int) {
        requireFragmentManager().beginTransaction().apply {
            replace(R.id.fragment_host_id, HeroDetailFragment.newInstance(id), HeroSquadActivity.FRAGMENT_TAG)
        }
            .addToBackStack(HeroesFragment::class.simpleName)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HeroesFragment()
    }
}
