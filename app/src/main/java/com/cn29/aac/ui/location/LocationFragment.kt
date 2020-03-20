package com.cn29.aac.ui.location

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.cn29.aac.binding.CustomConverter
import com.cn29.aac.databinding.FragmentLocationBinding
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.repo.util.Resource
import com.cn29.aac.repo.util.Status
import com.cn29.aac.ui.base.BaseFragment
import com.cn29.aac.ui.common.FragmentPermissionComponent
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback
import com.cn29.aac.ui.location.vm.LastLocationViewModel
import java.util.*
import javax.inject.Inject

class LocationFragment : BaseFragment() {
    @Inject
    lateinit var lastLocationViewModel: LastLocationViewModel

    @Inject
    lateinit var fragmentPermissionComponent: FragmentPermissionComponent

    @Inject
    lateinit var permissionCallback: PermissionCallback

    @Inject
    lateinit var binding: FragmentLocationBinding
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //get the viewmodel from activity
        lastLocationViewModel.lastKnowLocation
                .observe(viewLifecycleOwner,
                         Observer { location: Location? ->
                             binding.location = location
                         })
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding.converter = CustomConverter()
        binding.btnLocation.setOnClickListener { _: View? ->
            lastLocationViewModel.getSearchResult("jack+johnson")
                    .observe(viewLifecycleOwner,
                             Observer { result: Resource<List<Artist>> ->
                                 when (result.status) {
                                     Status.SUCCESS -> progressDialogComponent!!.hideLoading()
                                     Status.ERROR -> progressDialogComponent!!.hideLoading()
                                     Status.LOADING -> progressDialogComponent!!.showLoading()
                                     Status.CHECKING -> progressDialogComponent?.showLoading()
                                 }
                             })
            val location = binding.location
            if (location != null) {
                Toast.makeText(activity,
                               String.format(Locale.ENGLISH,
                                             "Lat : %f, Long : %f",
                                             location.latitude,
                                             location.longitude),
                               Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}