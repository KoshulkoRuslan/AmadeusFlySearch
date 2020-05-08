package com.example.myapplication.ui.startSearchFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import com.example.myapplication.databinding.FragmentPassengerParamDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class PassengerParamDialogFragment extends BottomSheetDialogFragment {
    private FragmentPassengerParamDialogBinding binding;
    public static final String PASSENGER_INFO ="PASSENGER_INFO";
    public static final String ADULT_COUNT ="ADULT_COUNT";
    public static final String CHILD_COUNT ="CHILD_COUNT";
    public static final String BABY_COUNT ="BABY_COUNT";
    public static final String TRAVEL_CLASS ="TRAVEL_CLASS";
    public String ticketClass = "ECONOMY";
    int adultCount = 1;
    int childCount = 0;
    int babyCount = 0;


    public PassengerParamDialogFragment(int adultCount, int childCount, int babyCount,String ticketClass) {
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.babyCount = babyCount;
        this.ticketClass = ticketClass;
    }

    public static PassengerParamDialogFragment newInstance(int adultCount, int childCount, int babyCount,String ticketClass) {
        final PassengerParamDialogFragment fragment = new PassengerParamDialogFragment(adultCount,childCount,babyCount,ticketClass);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPassengerParamDialogBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.textAdultCount.setText(String.valueOf(adultCount));
        binding.textChildCount.setText(String.valueOf(childCount));
        binding.textBabyCount.setText(String.valueOf(babyCount));
        setTicketClass(ticketClass);

        binding.bttnAddAdult.setOnClickListener(v -> {
            if (isMaxCount()){
                adultCount ++;
                binding.textAdultCount.setText(String.valueOf(adultCount));
            }
        });
        binding.bttnReduceAdult.setOnClickListener(v -> {
            if (adultCount>1){
                if (adultCount == babyCount){
                    babyCount--;
                    binding.textBabyCount.setText(String.valueOf(babyCount));
                }
                adultCount--;
                binding.textAdultCount.setText(String.valueOf(adultCount));
            }
        });

        binding.bttnAddChild.setOnClickListener(v -> {
            if (isMaxCount()){
                childCount ++;
                binding.textChildCount.setText(String.valueOf(childCount));
            }
        });
        binding.bttnReduceChild.setOnClickListener(v -> {
            if (childCount>0){
                childCount--;
                binding.textChildCount.setText(String.valueOf(childCount));
            }
        });

        binding.bttnAddBaby.setOnClickListener(v -> {
            if (babyCount<adultCount && isMaxCount()){
                babyCount ++;
                binding.textBabyCount.setText(String.valueOf(babyCount));
            }
            else {Toast.makeText(getContext(),"Количество младенцев не может быть больше количества взрослых.",Toast.LENGTH_LONG).show();}
        });
        binding.bttnReduceBaby.setOnClickListener(v -> {
            if (babyCount>0){
                babyCount --;
                binding.textBabyCount.setText(String.valueOf(babyCount));
            }
        });

        binding.applyButton.setOnClickListener(v -> {
            setResult();
            this.dismiss();
        });
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        setResult();
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        setResult();
        super.onDismiss(dialog);
    }

    public Boolean isMaxCount(){
        if ((adultCount + childCount + babyCount)>=9){
            Toast.makeText(getContext(),"Нельзя добавить больше 9 пассажиров.",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public String getTicketClass(){
        int checkedId = binding.chipGroup.getCheckedChipId();
        if (checkedId == binding.chipEconomy.getId())
            return "ECONOMY";
        else if (checkedId == binding.chipPremium.getId())
            return "PREMIUM_ECONOMY";
        else if (checkedId == binding.chipBusiness.getId())
            return "BUSINESS";
        else if (checkedId == binding.chipFirst.getId())
            return "FIRST";
       return "ECONOMY";
    }

    public void setTicketClass(String ticketClass){

        switch (ticketClass){
            case ("ECONOMY"):
                binding.chipEconomy.setChecked(true);
                break;
            case ("PREMIUM_ECONOMY"):
                binding.chipPremium.setChecked(true);
                break;
            case ("BUSINESS"):
                binding.chipBusiness.setChecked(true);
                break;
            case ("FIRST"):
                binding.chipFirst.setChecked(true);
                break;
        }

    }

    public void setResult(){
        Bundle result = new Bundle();
        result.putInt(ADULT_COUNT,adultCount);
        result.putInt(CHILD_COUNT,childCount);
        result.putInt(BABY_COUNT,babyCount);
        result.putString(TRAVEL_CLASS,getTicketClass());
        getParentFragmentManager().setFragmentResult(PASSENGER_INFO,result);
    }

}