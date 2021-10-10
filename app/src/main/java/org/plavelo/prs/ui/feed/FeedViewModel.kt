package org.plavelo.prs.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.plavelo.prs.domain.feed.Feed
import org.plavelo.prs.usecase.FeedUseCase
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedUseCase: FeedUseCase,
) : ViewModel() {

    val list: LiveData<List<Feed>> =
        feedUseCase
            .list()
            .asLiveData()

    fun add(feed: Feed) = viewModelScope.launch {
        feedUseCase.add(feed)
    }
}
