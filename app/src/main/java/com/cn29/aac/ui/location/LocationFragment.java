package com.cn29.aac.ui.location;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.cn29.aac.binding.CustomConverter;
import com.cn29.aac.databinding.FragmentLocationBinding;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.common.FragmentPermissionComponent;
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback;
import com.cn29.aac.ui.location.vm.LastLocationViewModel;
import java.util.Locale;
import javax.inject.Inject;


/**
 * Use the {@link LocationFragment#} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends BaseFragment {

  @Inject
  LastLocationViewModel lastLocationViewModel;

  @Inject
  FragmentPermissionComponent fragmentPermissionComponent;

  @Inject
  PermissionCallback permissionCallback;

  @Inject
  FragmentLocationBinding binding;

  public LocationFragment() {
    // Required empty public constructor
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //get the viewmodel from activity
    lastLocationViewModel.getLastKnowLocation()
        .observe(this, location -> binding.setLocation(location));
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding.setConverter(new CustomConverter());
    binding.btnLocation.setOnClickListener(view -> {
      Location location = binding.getLocation();
      if (location != null) {
        Toast.makeText(getActivity(),
            String.format(Locale.ENGLISH, "Lat : %f, Long : %f", location.getLatitude(),
                location.getLongitude()),
            Toast.LENGTH_SHORT).show();
      }
    });
    return binding.getRoot();
  }

}
