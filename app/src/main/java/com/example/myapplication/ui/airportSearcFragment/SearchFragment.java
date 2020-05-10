package com.example.myapplication.ui.airportSearcFragment;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databases.AppCodeIataDatabase;
import com.example.myapplication.apimodels.citymodel.AutocompleteAirportSearch;
import com.example.myapplication.databinding.FragmentSearchAirportBinding;
import com.google.android.material.transition.MaterialContainerTransform;

import java.util.List;

public class SearchFragment extends Fragment {
    FragmentSearchAirportBinding binding;
    MaterialContainerTransform sharedElementEnterTransition;
    RecyclerView recyclerView;
    EditText editText;
    AutocompleteSearchViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AutocompleteSearchViewModel.class);
        sharedElementEnterTransition = new MaterialContainerTransform(requireContext());
        setSharedElementEnterTransition(sharedElementEnterTransition);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchAirportBinding.inflate(inflater,container,false);
        recyclerView = binding.recyclerSearchView;
        editText = binding.searchTextField;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textInputLayout.setStartIconDrawable(R.drawable.plane_icon_animation);
        if (getArguments().getString("TAG").equals("To")){
            binding.textInputLayout.setHint("Куда");
            AnimatedVectorDrawable an = (AnimatedVectorDrawable)binding.textInputLayout.getStartIconDrawable();
            an.start();
        }





        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText,InputMethodManager.SHOW_IMPLICIT);
        AutocompleteAirportAdapter adapter = new AutocompleteAirportAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new AutocompleteAirportAdapter.OnItemClick() {
            @Override
            public void setOmItemClick(AutocompleteAirportSearch item) {
                Bundle bundle = new Bundle();
                if (getArguments().getString("TAG").equals("From")){
                    bundle.putString("TAG","From");
                    bundle.putString("City", item.getCityName());
                    bundle.putString("AirportCode", item.getAirportCode());
                }
                if (getArguments().getString("TAG").equals("To")){
                    bundle.putString("TAG","To");
                    bundle.putString("City", item.getCityName());
                    bundle.putString("AirportCode", item.getAirportCode());
                }
                getParentFragmentManager().setFragmentResult("FromTo", bundle);
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).popBackStack();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!String.valueOf(s).equals("")) {
                    Log.d("TAG", "onTextChanged: " + start + " " + before + " " + count);
                    List<AutocompleteAirportSearch> list = viewModel.getAutocompleteAirports(String.valueOf(s));
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}
