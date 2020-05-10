package com.example.myapplication.ui.flyOfferResultFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.apimodels.flyOffer.FlyQueryModel;
import com.example.myapplication.databinding.FlyOfferResultFragmentBinding;
import com.google.android.material.transition.MaterialContainerTransform;

import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.ADULT_COUNT;
import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.BABY_COUNT;
import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.CHILD_COUNT;
import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.DEPARTURE_DATE;
import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.DESTINATION_CODE;
import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.ORIGIN_CODE;
import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.RETURN_DATE;
import static com.example.myapplication.ui.startSearchFragment.StartSearchFragment.TRAVEL_CLASS;

public class FlyOfferResultFragment extends Fragment {
    FlyOfferResultFragmentBinding binding;
    FlyOfferResultViewModel vm;
    FlyQueryModel queryModel;
    FlySearchAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queryModel = new FlyQueryModel(getArguments().getString(ORIGIN_CODE),
                getArguments().getString(DESTINATION_CODE),
                getArguments().getString(DEPARTURE_DATE),
                getArguments().getString(RETURN_DATE),
                getArguments().getInt(ADULT_COUNT),
                getArguments().getInt(CHILD_COUNT),
                getArguments().getInt(BABY_COUNT),
                getArguments().getString(TRAVEL_CLASS),
                "RUB");
        vm = new ViewModelProvider(this).get(FlyOfferResultViewModel.class);
        vm.setQueryModel(queryModel);
        MaterialContainerTransform materialContainerTransform = new MaterialContainerTransform(requireContext());
        setSharedElementEnterTransition(materialContainerTransform);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FlyOfferResultFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String travelTitle = queryModel.getOriginLocationCode() + "-" + queryModel.getDestinationLocationCode();
        String travelDate = vm.getDateInTitle();
        binding.topAppBar.setTitle(travelTitle + ", " + travelDate);
        showProgress();
        binding.topAppBar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).popBackStack();
        });
        adapter = new FlySearchAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        vm.getFlyOffers().observe(this,flySearchModel -> {
            adapter.setData(flySearchModel);
            adapter.notifyDataSetChanged();
            hideProgress();
            if (flySearchModel.getData().size() == 0){
                binding.textError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showProgress(){
        binding.progressBar2.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        binding.progressBar2.setVisibility(View.GONE);
    }

}
