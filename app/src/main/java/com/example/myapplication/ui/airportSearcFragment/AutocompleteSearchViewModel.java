package com.example.myapplication.ui.airportSearcFragment;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.apimodels.citymodel.AutocompleteAirportSearch;
import com.example.myapplication.repository.StartSearchRepository;

import java.util.List;

public class AutocompleteSearchViewModel extends AndroidViewModel {
    Context context;
    StartSearchRepository repository;

    public AutocompleteSearchViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        repository = new StartSearchRepository(context);

    }

    public List<AutocompleteAirportSearch> getAutocompleteAirports(String valueOf) {
        List<AutocompleteAirportSearch> result = repository.getAutocompleteAirports(valueOf);
        return result;
    }
}
