package com.cn29.aac.ui.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cn29.aac.databinding.FragmentAlbumBinding
import com.cn29.aac.di.scope.AndroidDataBinding
import com.cn29.aac.di.scope.ViewModel
import com.cn29.aac.repo.itunes.Album
import com.cn29.aac.repo.util.Resource
import com.cn29.aac.repo.util.Status
import com.cn29.aac.ui.base.BaseFragment
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter
import com.cn29.aac.ui.shopping.vm.AlbumFragmentViewModel
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel
import javax.inject.Inject

class AlbumFragment : BaseFragment() {
    @Inject
    @ViewModel
    lateinit var viewModel: AlbumFragmentViewModel

    @Inject
    @ViewModel
    lateinit var shoppingActivityViewModel: ShoppingActivityViewModel

    @Inject
    @AndroidDataBinding
    lateinit var binding: FragmentAlbumBinding
    private var adapter: AlbumAdapter? = null
    private var albums: List<Album>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var artistId = 909253 //default value
        var entity: String? = "album" //default value
        if (arguments?.containsKey("artistId") == true && arguments?.containsKey(
                        "entity") == true) {
            artistId = arguments!!.getInt("artistId")
            entity = arguments!!.getString("entity")
        }
        viewModel
                .getAlbumResult(artistId, entity)
                .observe(this,
                         Observer { response: Resource<List<Album>> ->
                             when (response.status) {
                                 Status.CHECKING -> {
                                 }
                                 Status.SUCCESS -> {
                                     progressDialogComponent!!.hideLoading()
                                     albums = response.data
                                     adapter = AlbumAdapter(
                                             albums!!,
                                             albumOnItemClickListener)
                                     binding.list.adapter = adapter
                                 }
                                 Status.ERROR -> progressDialogComponent!!.hideLoading()
                                 Status.LOADING -> progressDialogComponent!!.showLoading()
                             }
                         })
    }

    //add to the kart
    private val albumOnItemClickListener: BaseRecyclerViewAdapter.OnItemClickListener<Album>
        get() = object : BaseRecyclerViewAdapter.OnItemClickListener<Album> {
            override fun onItemClick(item: Album) {
                //add to the kart
                Toast.makeText(
                                activity,
                                "Implement your own",
                                Toast.LENGTH_SHORT)
                        .show()
            }
        }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return binding.root
    }
}