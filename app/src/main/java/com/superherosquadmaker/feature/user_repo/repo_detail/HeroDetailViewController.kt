package com.superherosquadmaker.feature.user_repo.repo_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superherosquadmaker.domain.ChangeHeroSquadUseCase
import com.superherosquadmaker.domain.FetchHeroUseCase
import com.superherosquadmaker.feature.user_repo.repo_detail.viewstate.ErrorViewState
import com.superherosquadmaker.feature.user_repo.repo_detail.viewstate.HeroSquadButtonViewState
import com.superherosquadmaker.feature.user_repo.viewstate.HeroViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroDetailViewController @Inject constructor(
    private val fetchRepoDetailUseCase: FetchHeroUseCase,
    private val changeHeroSquadUSeCase: ChangeHeroSquadUseCase,
    private val presenter: IHeroDetailPresenter
) : ViewModel(), HeroDetailView {

    private var onViewReadyCalled = false

    var isInSquad = false
    private val _livedataHeroDetail = MutableLiveData<HeroViewState>()
    val livedataHeroDetail: LiveData<HeroViewState> = _livedataHeroDetail

    private val _livedataError = MutableLiveData<ErrorViewState>()
    val livedataError: LiveData<ErrorViewState> = _livedataError

    private val _livedataButtonState = MutableLiveData<HeroSquadButtonViewState>()
    val livedataButtonState: LiveData<HeroSquadButtonViewState> = _livedataButtonState

    fun onCreate() {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
        super.onCleared()
    }

    fun onViewReady(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (!onViewReadyCalled) {
            fetchRepoDetailUseCase.fetchHeroUseCase(id)
            onViewReadyCalled = true
        }
    }

    override fun displayRepositoryDetail(value: HeroViewState) {
        _livedataHeroDetail.postValue(value)
    }

    override fun displayError() {
        _livedataError.postValue(ErrorViewState())
    }

    override fun displayButton(heroSquadButtonViewState: HeroSquadButtonViewState) {
        isInSquad = heroSquadButtonViewState.isInSquad
        _livedataButtonState.postValue(heroSquadButtonViewState)
    }

    fun onSquadButtonClicked(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        changeHeroSquadUSeCase.changeHeroSquad(id)
    }
}