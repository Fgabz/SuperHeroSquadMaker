package com.superherosquadmaker.feature.user_repo.repo_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.superherosquadmaker.R
import com.superherosquadmaker.design.ChooserBuilder
import com.superherosquadmaker.design.showChooserDialog
import com.superherosquadmaker.di.IDaggerFactoryViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_hero_detail.*
import javax.inject.Inject

class HeroDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: IDaggerFactoryViewModel

    private lateinit var viewController: HeroDetailViewController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewController = ViewModelProvider(this, viewModelFactory).get(HeroDetailViewController::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewController.onCreate()
        val id = arguments?.getInt(HERO_ID) ?: 0


        setupObservers()

        viewController.onViewReady(id)

        button.setOnClickListener {
            if (viewController.isInSquad) {
                showChooserDialog(requireActivity()) {
                    addAction(R.string.removeHeroFromSquadText, R.color.redMarvel) {
                        viewController.onSquadButtonClicked(id)
                    }


                    setOnCancelListener(R.string.globalCancel) {
                        it.close()
                    }
                }
            } else {
                viewController.onSquadButtonClicked(id)
            }
        }
    }

    private fun setupObservers() {
        viewController.livedataHeroDetail.observe(this, Observer { viewState ->
            heroDescriptionText.text = viewState.description
            heroName.text = viewState.name
            avatarHero.load(viewState.urlImage)
        })

        viewController.livedataError.observe(this, Observer { viewState ->
            Toast.makeText(requireContext(), viewState.message, Toast.LENGTH_SHORT).show()
        })

        viewController.livedataButtonState.observe(this, Observer { viewState ->
            button.visibility = View.VISIBLE
            button.background = requireContext().getDrawable(viewState.backgroundRes)
            button.text = requireContext().getString(viewState.messageRes)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_hero_detail, container, false)

    companion object {
        private const val HERO_ID = "HERO_ID"

        @JvmStatic
        fun newInstance(id: Int) = HeroDetailFragment().apply {
            val args = Bundle()
            args.putInt(HERO_ID, id)

            arguments = args
        }
    }
}
