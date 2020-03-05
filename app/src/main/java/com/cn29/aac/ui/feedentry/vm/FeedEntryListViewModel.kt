package com.cn29.aac.ui.feedentry.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.repo.feedentry.FeedEntryRepository
import java.util.*

/**
 * Created by Charles Ng on 6/9/2017.
 */
class FeedEntryListViewModel(
        //list all your repository
        private val feedEntryDBRepository: FeedEntryRepository) :
        ViewModel() {
    //list all your live data here
    @JvmField
    var feedEntries: LiveData<List<FeedEntry?>?> = MutableLiveData()
    private val userId = MutableLiveData<String>()

    //show your composite model here
    private val compositeModelLiveData: MediatorLiveData<CompositeModel?>

    //Data binding variable
    @JvmField
    val feedEntry: ObservableField<FeedEntry>
    fun loadUserId(userId: String) {
        this.userId.value = userId
    }

    val feedEntrys: LiveData<List<FeedEntry?>?>?
        get() = feedEntryDBRepository.getAll()

    val compositeEntrys: LiveData<CompositeModel?>
        get() = compositeModelLiveData

    fun insert(vararg feedEntries: FeedEntry?): LiveData<List<FeedEntry?>?>? {
        feedEntryDBRepository.insertAll(*feedEntries)
        return feedEntryDBRepository.getAll()
    }

    fun delete(feedEntry: FeedEntry?) {
        feedEntryDBRepository.delete(feedEntry)
    }

    fun update(feedEntry: FeedEntry?): Int {
        return feedEntryDBRepository.update(feedEntry)!!
    }

    /*
    The complete model to show the data
     */
    class CompositeModel {
        @JvmField
        var userId = "SystemId"

        @JvmField
        var feedEntries: List<FeedEntry?>? = ArrayList()

    }

    init {
        feedEntries = feedEntryDBRepository.getAll()!!
        compositeModelLiveData = MediatorLiveData()
        compositeModelLiveData.addSource(
                feedEntries
        ) { data: List<FeedEntry?>? ->
            val compositeModel = compositeModelLiveData.value
            compositeModel!!.feedEntries = data
            compositeModelLiveData.postValue(compositeModel)
        }
        compositeModelLiveData.addSource(userId
        ) { data: String ->
            val compositeModel = compositeModelLiveData.value
            compositeModel!!.userId = data
            compositeModelLiveData.postValue(
                    compositeModel)
        }
        //initialize the composite model to avoid NullPointerException
        compositeModelLiveData.postValue(CompositeModel())
        feedEntry = ObservableField()
    }
}