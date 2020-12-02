package com.superherosquadmaker.feature.user_repo.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superherosquadmaker.domain.FetchMarvelHeroListUseCase
import com.superherosquadmaker.domain.FetchSquadListUseCase
import com.superherosquadmaker.feature.user_repo.viewstate.HeroViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HeroViewController @Inject constructor(
    private val fetchMarvelHeroUseCase: FetchMarvelHeroListUseCase,
    private val fetchSquadListUseCase: FetchSquadListUseCase,
    private val presenter: IHeroPresenter
) : ViewModel(), HeroView {

    private var onViewReadyCalled = false

    private val _mutableHeroList = MutableLiveData<List<HeroViewState>>()
    val mutableHeroList: LiveData<List<HeroViewState>> = _mutableHeroList

    private val _mutableSquadList = MutableLiveData<List<HeroViewState>>()
    val mutableSquadList: LiveData<List<HeroViewState>> = _mutableSquadList


    fun onCreate() {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
        super.onCleared()
    }

    fun onViewReady() = viewModelScope.launch(Dispatchers.IO) {
        if (!onViewReadyCalled) {
            fetchMarvelHeroUseCase.fetchMarvelHeroList()
            onViewReadyCalled = true
        }
        fetchSquadListUseCase.fetchSquadList()
    }

    override fun displayList(value: List<HeroViewState>) {
        _mutableHeroList.postValue(value)
    }

    override fun displayError() {
        Timber.e("Error displaying list")
    }

    override fun displaySquad(squadList: List<HeroViewState>) {
        _mutableSquadList.postValue(squadList)
    }

    override fun hideSquad() {
        _mutableSquadList.postValue(emptyList())
    }
}