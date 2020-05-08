package com.example.myapplication.ui.startSearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import com.example.myapplication.R;
import com.example.myapplication.apimodels.flyOffer.FlyQueryModel;
import com.example.myapplication.databinding.StartSearchFragmentBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.transition.Hold;


public class StartSearchFragment extends Fragment {
    public static final String PASSENGER_INFO = "PASSENGER_INFO";
    public static final String ADULT_COUNT = "ADULT_COUNT";
    public static final String CHILD_COUNT = "CHILD_COUNT";
    public static final String BABY_COUNT = "BABY_COUNT";
    public static final String TRAVEL_CLASS = "TRAVEL_CLASS";
    public static final String ORIGIN_CODE = "ORIGIN_CODE";
    public static final String DESTINATION_CODE = "DESTINATION_CODE";
    public static final String DEPARTURE_DATE = "DEPARTURE_DATE";
    public static final String RETURN_DATE = "RETURN_DATE";

    StartSearchFragmentBinding binding;
    StartSearchViewModel vm;
    MaterialDatePicker<Pair<Long, Long>> picker;
    MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>> dateListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setExitTransition(new Hold());
        vm = new ViewModelProvider(this).get(StartSearchViewModel.class);
        Pair<Long, Long> pair = new Pair<>(System.currentTimeMillis(), System.currentTimeMillis());
        picker = MaterialDatePicker.Builder.dateRangePicker().setSelection(pair).build();
        dateListener = selection -> setTime(selection);

        getParentFragmentManager().setFragmentResultListener("FromTo", this, (requestKey, result) -> {
            if (result.getString("TAG").equals("From")) {
                binding.textFieldFrom.setText(result.getString("City") + ", " + result.getString("AirportCode"));
                vm.queryModel.setOriginLocationCode(result.getString("AirportCode"));
            }
            if (result.getString("TAG").equals("To")) {
                binding.textFieldTo.setText(result.getString("City") + ", " + result.getString("AirportCode"));
                vm.queryModel.setDestinationLocationCode(result.getString("AirportCode"));
            }
        });

        getParentFragmentManager().setFragmentResultListener(PASSENGER_INFO, this, (requestKey, result) -> {
            vm.queryModel.setAdults(result.getInt(ADULT_COUNT));
            vm.queryModel.setChildren(result.getInt(CHILD_COUNT));
            vm.queryModel.setInfants(result.getInt(BABY_COUNT));
            vm.queryModel.setTravelClass(result.getString(TRAVEL_CLASS));
            int passengerCount = vm.getPassengerCount();
            binding.buttonPassengerSet.setText(passengerCount + " " + getResources().getQuantityString(R.plurals.passengers, passengerCount)
                    + ", " + vm.getTicketClassName(vm.queryModel.getTravelClass()));
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StartSearchFragmentBinding.inflate(inflater);
        View v = inflater.inflate(R.layout.start_search_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonPassengerSet.setText(vm.getPassengerCount() + " " + getResources().getQuantityString(R.plurals.passengers, vm.getPassengerCount())
                + ", " + vm.getTicketClassName(vm.queryModel.getTravelClass()));

        binding.buttonPassengerSet.setOnClickListener(v -> {
            showPassengerFragment();
        });

        binding.textFieldFrom.setOnClickListener(v -> {
            view.findViewById(R.id.textTo).setTransitionName("");
            view.findViewById(R.id.textFrom).setTransitionName("animationFromTo");
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(view.findViewById(R.id.textFrom), "animationFromTo")
                    .build();
            Bundle bundle = new Bundle();
            bundle.putString("TAG", "From");
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_startSearchFragment_to_searchFragment2, bundle, null, extras);
        });


        binding.textFieldTo.setOnClickListener(v -> {
            view.findViewById(R.id.textFrom).setTransitionName("");
            view.findViewById(R.id.textTo).setTransitionName("animationFromTo");
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(view.findViewById(R.id.textTo), "animationFromTo")
                    .build();
            Bundle bundle = new Bundle();
            bundle.putString("TAG", "To");
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_startSearchFragment_to_searchFragment2, bundle, null, extras);
        });

        binding.textFieldStartDate.setOnClickListener(v -> {
            picker.show(getParentFragmentManager(), "Picker");
            picker.addOnPositiveButtonClickListener(dateListener);
        });

        binding.searchButton.setOnClickListener(v -> {
            FlyQueryModel queryModel = vm.queryModel;
            Bundle arg = new Bundle();
            arg.putString(ORIGIN_CODE,queryModel.getOriginLocationCode());
            arg.putString(DESTINATION_CODE,queryModel.getDestinationLocationCode());
            arg.putString(DEPARTURE_DATE,queryModel.getDepartureDate());
            arg.putString(RETURN_DATE,queryModel.getReturnDate());
            arg.putInt(ADULT_COUNT,queryModel.getAdults());
            arg.putInt(CHILD_COUNT,queryModel.getChildren());
            arg.putInt(BABY_COUNT,queryModel.getInfants());
            arg.putString(TRAVEL_CLASS,queryModel.getTravelClass());
            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.action_startSearchFragment_to_flyOfferResultFragment,arg,null,null);

        });

    }

    public void setTime(Pair<Long, Long> selection) {
        vm.setDepartureDateMillSec(selection.first);
        vm.setReturnDateMillSec(selection.second);
        String departureDate = vm.convertDateToView(selection.first);
        String returnDate = vm.convertDateToView(selection.second);
        binding.textFieldStartDate.setText(departureDate);
        binding.textFieldEndDate.setText(returnDate);
    }

    public void showPassengerFragment() {
        PassengerParamDialogFragment fragment = PassengerParamDialogFragment.newInstance(
                vm.queryModel.getAdults(),
                vm.queryModel.getChildren(),
                vm.queryModel.getInfants(),
                vm.queryModel.getTravelClass());

        fragment.show(getParentFragmentManager(), PASSENGER_INFO);
    }
}
