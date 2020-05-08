package com.example.myapplication.ui.airportSearcFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.apimodels.citymodel.AutocompleteAirportSearch;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteAirportAdapter extends RecyclerView.Adapter<AutocompleteAirportAdapter.AutocompleteAirportHolder> {
    List<AutocompleteAirportSearch> list = new ArrayList<>();
    OnItemClick listener;

    public void setListener(OnItemClick listener) {
        this.listener = listener;
    }

    public interface OnItemClick{
        void setOmItemClick(AutocompleteAirportSearch item);
    }

    @NonNull
    @Override
    public AutocompleteAirportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airport,parent,false);
        return new AutocompleteAirportHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AutocompleteAirportHolder holder, int position) {
        AutocompleteAirportSearch item = list.get(position);
        if (item.getAirportName() != null && item.getAirportName().equals("0")){
            holder.textAirportName.setText("Любой аэропорт");
        }
        else {
            holder.textAirportName.setText(item.getAirportName());
        }
        holder.textCityName.setText(item.getCityName());
        holder.textAirportCode.setText(item.getAirportCode());
        holder.textCountryCode.setText(item.getCountryCode());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AutocompleteAirportHolder extends RecyclerView.ViewHolder{
        TextView textCityName;
        TextView textAirportName;
        TextView textCountryCode;
        TextView textAirportCode;

        public AutocompleteAirportHolder(@NonNull View itemView) {
            super(itemView);
            textCityName = itemView.findViewById(R.id.textCityName);
            textAirportName = itemView.findViewById(R.id.textAirportName);
            textCountryCode = itemView.findViewById(R.id.textCountryCode);
            textAirportCode = itemView.findViewById(R.id.textAirportCode);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.setOmItemClick(list.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public List<AutocompleteAirportSearch> getList() {
        return list;
    }

    public void setList(List<AutocompleteAirportSearch> list) {
        this.list = list;
    }
}
