package com.example.myapplication.ui.flyOfferResultFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.apimodels.flyOffer.Datum;
import com.example.myapplication.apimodels.flyOffer.FlySearchModel;
import com.example.myapplication.apimodels.flyOffer.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class FlySearchAdapter extends RecyclerView.Adapter<FlySearchAdapter.FlySearchHolder> {
    List<Datum> list = new ArrayList<>();
    Context context;

    public FlySearchAdapter(Context context) {
        this.context = context;
    }

    public void setData(FlySearchModel flySearch) {
        this.list = flySearch.getData();
    }

    @NonNull
    @Override
    public FlySearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fly_search,parent,false);
        return new FlySearchHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FlySearchHolder holder, int position) {

        List<Segment> segmentsRight = list.get(position).getItineraries().get(0).getSegments();
        List<Segment> segmentsBack = list.get(position).getItineraries().get(1).getSegments();

        int segmentSizeRight = segmentsRight.size();
        int segmentSizeBack = segmentsBack.size();

        String flyRight = segmentsRight.get(0).getDeparture().getIataCode() + " - "
                + segmentsRight.get(segmentSizeRight - 1).getArrival().getIataCode();
        String flyBack = segmentsBack.get(0).getDeparture().getIataCode() + " - "
                + segmentsBack.get(segmentSizeBack - 1).getArrival().getIataCode();

        String departureTimeRight_= sheduleTime(segmentsRight.get(0).getDeparture().getAt());
        String arrivalTimeRight_= sheduleTime(segmentsRight.get(segmentSizeRight - 1).getArrival().getAt());
        String departureTimeBack_= sheduleTime(segmentsBack.get(0).getDeparture().getAt());
        String arrivalTimeBack_= sheduleTime(segmentsBack.get(segmentSizeBack - 1).getArrival().getAt());

        String durationFlyRight = flyDuration(list.get(position).getItineraries().get(0).getDuration());
        String durationFlyBack =flyDuration(list.get(position).getItineraries().get(1).getDuration());

        String totalPrice_ = list.get(position).getPrice().getTotal() + " " + list.get(position).getPrice().getCurrency();



        holder.titleFlyRight.setText(flyRight);
        holder.titleFlyBack.setText(flyBack);
        holder.departureTimeRight.setText(departureTimeRight_);
        holder.arrivalTimeRight.setText(arrivalTimeRight_);
        holder.departureTimeBack.setText(departureTimeBack_);
        holder.arrivalTimeBack.setText(arrivalTimeBack_);
        holder.durationTimeRight.setText(durationFlyRight);
        holder.durationTimeBack.setText(durationFlyBack);
        holder.segmentMapRight.setSegment(list.get(position).getItineraries(),0);
        holder.segmentMapBack.setSegment(list.get(position).getItineraries(),1);
        holder.totalPrice.setText(totalPrice_);

        TreeSet<String> unrepeatedCarrier = new TreeSet<>();
        unrepeatedCarrier.addAll(holder.segmentMapRight.getCarrier());
        unrepeatedCarrier.addAll(holder.segmentMapBack.getCarrier());
        ArrayList<String> carriers = new ArrayList<>(unrepeatedCarrier);
        for (String carrier:carriers) {
            Log.d("TAG", "carrier " + carrier);
        }
        holder.iconCarrier2.setVisibility(View.GONE);
        holder.iconCarrier3.setVisibility(View.GONE);

        for (int i = 0; i < carriers.size(); i++){
            if (i<3){
                holder.iconCarriers.get(i).setVisibility(View.VISIBLE);
                Glide
                        .with(context)
                        .load("https://pics.avs.io/200/200/"+ carriers.get(i) +".png")
                        .into(holder.iconCarriers.get(i));
            }

        }
        

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FlySearchHolder extends RecyclerView.ViewHolder{
        TextView titleFlyRight;
        TextView durationTimeRight;
        TextView departureTimeRight;
        TextView arrivalTimeRight;
        SegmentMap segmentMapRight;
        TextView titleFlyBack;
        TextView durationTimeBack;
        TextView departureTimeBack;
        TextView arrivalTimeBack;
        SegmentMap segmentMapBack;
        TextView totalPrice;
        ImageView iconCarrier1;
        ImageView iconCarrier2;
        ImageView iconCarrier3;
        ArrayList<ImageView> iconCarriers;


        public FlySearchHolder(@NonNull View itemView) {
            super(itemView);
            titleFlyRight = itemView.findViewById(R.id.textTitleFlyRight);
            titleFlyBack = itemView.findViewById(R.id.textTitleFlyBack);
            durationTimeRight = itemView.findViewById(R.id.textDurationTimeRight);
            durationTimeBack = itemView.findViewById(R.id.textDurationTimeBack);
            departureTimeRight = itemView.findViewById(R.id.textDepartureTimeRight);
            departureTimeBack = itemView.findViewById(R.id.textDepartureTimeBack);
            arrivalTimeRight = itemView.findViewById(R.id.textArrivalTimeRight);
            arrivalTimeBack = itemView.findViewById(R.id.textArrivalTimeBack);
            segmentMapRight = itemView.findViewById(R.id.segmentMapRight);
            segmentMapBack = itemView.findViewById(R.id.segmentMapBack);
            totalPrice = itemView.findViewById(R.id.textTotalPrice);
            iconCarrier1 = itemView.findViewById(R.id.iconCarrier1);
            iconCarrier2 = itemView.findViewById(R.id.iconCarrier2);
            iconCarrier3 = itemView.findViewById(R.id.iconCarrier3);
            iconCarriers = new ArrayList<>();
            iconCarriers.add(iconCarrier1);
            iconCarriers.add(iconCarrier2);
            iconCarriers.add(iconCarrier3);

        }
    }

    private String sheduleTime(String string){
        String result = string.substring(11,16);
        return result;
    }

    private String flyDuration(String string){
        String hours = "00";
        String minutes = "00";
        if (string.length()>5){
            hours = string.substring(string.indexOf('T')+1,string.indexOf('H'));
            minutes = string.substring(string.indexOf('H')+1,string.indexOf('M'));
        } else {
            hours = string.substring(string.indexOf('T')+1,string.indexOf('H'));
            minutes = "00";
        }

        return hours + "ч " + minutes + "мин";
    }
}
