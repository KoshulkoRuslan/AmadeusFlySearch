package com.example.myapplication.ui.startSearchFragment;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.R;
import com.example.myapplication.apimodels.flyOffer.FlyQueryModel;

import java.util.Calendar;

public class StartSearchViewModel extends AndroidViewModel {
    private Context context;
    public FlyQueryModel queryModel;
    private long departureDateMillSec;
    private long returnDateMillSec;

    public StartSearchViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        queryModel = new FlyQueryModel();
    }

    public String getTicketClassName(String string) {
        String[] strings = context.getResources().getStringArray(R.array.tickets_class);
        switch (string) {
            case ("ECONOMY"):
                return strings[0];
            case ("PREMIUM_ECONOMY"):
                return strings[1];
            case ("BUSINESS"):
                return strings[2];
            case ("FIRST"):
                return strings[3];
        }
        return strings[0];
    }

    public String convertToDate(long time){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        String result = String.format("%d-%02d-%02d", c.get(Calendar.YEAR),
                c.get(Calendar.MONTH) + 1,
                +c.get(Calendar.DAY_OF_MONTH));
        return result;
    }
    public String convertDateToView(long time){
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(time);
        return String.format("%02d.%02d.%d", c1.get(Calendar.DAY_OF_MONTH),
                c1.get(Calendar.MONTH) + 1,
                +c1.get(Calendar.YEAR));
    }

    public int getPassengerCount(){
        return queryModel.getAdults() + queryModel.getChildren() + queryModel.getInfants();
    }


    public long getDepartureDateMillSec() {
        return departureDateMillSec;
    }

    public void setDepartureDateMillSec(long departureDateMillSec) {
        this.departureDateMillSec = departureDateMillSec;
        queryModel.setDepartureDate(convertToDate(departureDateMillSec));
    }

    public long getReturnDateMillSec() {
        return returnDateMillSec;
    }

    public void setReturnDateMillSec(long returnDateMillSec) {
        this.returnDateMillSec = returnDateMillSec;
        queryModel.setReturnDate(convertToDate(returnDateMillSec));
    }
}
