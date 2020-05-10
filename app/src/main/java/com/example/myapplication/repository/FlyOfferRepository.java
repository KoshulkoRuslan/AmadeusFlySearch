package com.example.myapplication.repository;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myapplication.FlyOfferApi;
import com.example.myapplication.apimodels.flyOffer.FlyQueryModel;
import com.example.myapplication.apimodels.flyOffer.FlySearchModel;
import com.example.myapplication.databases.FlyOfferBase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FlyOfferRepository {
    Retrofit retrofit;
    Context context;


    public FlyOfferRepository(Context context) {
        this.context = context;
        retrofit = FlyOfferBase.getInstance(context);
    }

    public LiveData<FlySearchModel> getFlyOffers(FlyQueryModel queryModel){
        MutableLiveData<FlySearchModel> liveData = new MutableLiveData<>();
        FlyOfferApi flyOfferApi = retrofit.create(FlyOfferApi.class);
        flyOfferApi.getFlyOffer(queryModel.getOriginLocationCode(),
                queryModel.getDestinationLocationCode(),
                queryModel.getDepartureDate(),
                queryModel.getReturnDate(),
                queryModel.getAdults(),
                queryModel.getChildren(),
                queryModel.getInfants(),
                queryModel.getTravelClass(),
                queryModel.getCurrencyCode()).enqueue(new Callback<FlySearchModel>() {
            @Override
            public void onResponse(Call<FlySearchModel> call, Response<FlySearchModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FlySearchModel> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return liveData;
    }


}
