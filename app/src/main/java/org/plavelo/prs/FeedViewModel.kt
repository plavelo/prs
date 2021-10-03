package org.plavelo.prs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.plavelo.prs.domain.rss.entity.Feed
import org.plavelo.prs.usecase.FeedUseCase
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedUseCase: FeedUseCase,
): ViewModel() {

    fun add(feed: Feed) = viewModelScope.launch {
        feedUseCase.add(feed)
    }
}
