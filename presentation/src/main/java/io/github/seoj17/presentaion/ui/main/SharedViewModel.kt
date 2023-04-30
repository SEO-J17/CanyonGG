package io.github.seoj17.presentaion.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.seoj17.presentaion.model.Summoner
import javax.inject.Inject

class SharedViewModel @Inject constructor() : ViewModel() {

    private val _representativeSummoner = MutableLiveData<Summoner>()
    val representativeSummoner: LiveData<Summoner> = _representativeSummoner

    private val _searchSummoner = MutableLiveData<Summoner>()
    val searchSummoner: LiveData<Summoner> = _searchSummoner

    fun fetchRepresentativeUser(summoner: Summoner) {
        _representativeSummoner.value = summoner
    }

    fun fetchSearchSummoner(summoner: Summoner) {
        _searchSummoner.value = summoner
    }
}
