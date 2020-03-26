package com.cn29.aac.ui.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cn29.aac.databinding.FragmentArtistBinding
import com.cn29.aac.di.scope.AndroidDataBinding
import com.cn29.aac.di.scope.ViewModel
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.repo.util.Resource
import com.cn29.aac.repo.util.Status
import com.cn29.aac.ui.base.BaseFragment
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModel
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel
import java.util.*
import javax.inject.Inject

class ArtistFragment : BaseFragment() {
    @Inject
    @ViewModel
    lateinit var viewModel: ArtistFragmentViewModel

    @Inject
    @ViewModel
    lateinit var shoppingActivityViewModel: ShoppingActivityViewModel

    @Inject
    @AndroidDataBinding
    lateinit var binding: FragmentArtistBinding
    private var adapter: ArtistAdapter? = null
    private var list: List<Artist> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoppingActivityViewModel
                .getQueryData()
                .observe(this,
                         Observer { query: String? ->
                             viewModel.getArtist(
                                             query)
                                     .observe(
                                             this,
                                             Observer { response: Resource<List<Artist>> ->
                                                 when (response.status) {
                                                     Status.CHECKING -> {
                                                     }
                                                     Status.SUCCESS -> {
                                                         progressDialogComponent!!.hideLoading()
                                                         list = response.data!!
                                                         val feedEntryOnItemClickListener = feedEntryOnItemClickListener
                                                         adapter = ArtistAdapter(
                                                                 list,
                                                                 feedEntryOnItemClickListener)
                                                         binding.list.adapter = adapter
                                                     }
                                                     Status.ERROR -> progressDialogComponent!!.hideLoading()
                                                     Status.LOADING -> progressDialogComponent!!.showLoading()
                                                 }
                                             })
                         }
                )
    }

    //open the artist detail
    private val feedEntryOnItemClickListener: BaseRecyclerViewAdapter.OnItemClickListener<Artist>
        get() = object : BaseRecyclerViewAdapter.OnItemClickListener<Artist> {
            override fun onItemClick(item: Artist) {
                val intent = Intent(
                        this@ArtistFragment.activity,
                        ArtistDetailActivity::class.java)
                intent.putExtra(
                        "artist",
                        item)
                startActivity(
                        intent)
            }

        }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding.list.layoutManager = GridLayoutManager(activity, 4)
        return binding.root
    }
}