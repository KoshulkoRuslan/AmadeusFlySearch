package com.example.myapplication.ui.flyOfferResultFragment;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.apimodels.flyOffer.FlyQueryModel;
import com.example.myapplication.apimodels.flyOffer.FlySearchModel;
import com.example.myapplication.repository.FlyOfferRepository;

import java.nio.channels.ScatteringByteChannel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FlyOfferResultViewModel extends AndroidViewModel {
    private Context context;
    private FlyQueryModel queryModel;
    private FlyOfferRepository repository;

    public FlyOfferResultViewModel(@NonNull Application application) {
        super(application);
        repository = new FlyOfferRepository(application.getApplicationContext());
    }

    public FlyQueryModel getQueryModel() {
        return queryModel;
    }

    public void setQueryModel(FlyQueryModel queryModel) {
        this.queryModel = queryModel;
    }

    public LiveData<FlySearchModel> getFlyOffers(){
        return repository.getFlyOffers(queryModel);
    }

    public String getDateInTitle(){
        String result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd");
        try{
            c1.setTime(sdf.parse(queryModel.getDepartureDate()));
            c2.setTime(sdf.parse(queryModel.getReturnDate()));
        }
        catch (Exception e){}

        result = String.format("%02d.%02d", c1.get(Calendar.DAY_OF_MONTH),
                c1.get(Calendar.MONTH) + 1)
                + "-" + String.format("%02d.%02d", c2.get(Calendar.DAY_OF_MONTH),
                c2.get(Calendar.MONTH) + 1);

        return result;
    }
}
