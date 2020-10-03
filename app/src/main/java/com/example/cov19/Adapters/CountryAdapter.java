package com.example.cov19.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cov19.Ui.CountryCov19;
import com.example.cov19.Models.Country;
import com.example.cov19.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    Context context ;
    ArrayList<Country> countryList;

    public CountryAdapter(Context context , ArrayList<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent,false);
        return new CountryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Country country = countryList.get(position);
        holder.layoutCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , CountryCov19.class);
                intent.putExtra("nameCountery" , country.getCountryName());
                intent.putExtra("latCountery" , country.getLatCountry());
                intent.putExtra("longCountery" , country.getLongCountry());
                intent.putExtra("imageCountery" , country.getImage());
                intent.putExtra("totalCasesOnCountery" , country.getTotalCases());
                intent.putExtra("deaths" , country.getDeaths());
                intent.putExtra("recovered" , country.getRecovered());
                context.startActivity(intent);
            }
        });
        holder.countryName.setText(country.getCountryName());
        Glide.with(context).
                load(country.getImage()).
                apply(new RequestOptions().override(240,160))
                .into(holder.countryImage);
       // holder.totalCases.setText(country.getTotalCases());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryName , totalCases;
        ImageView countryImage;
        RelativeLayout layoutCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutCountry = itemView.findViewById(R.id.layoutCountry);
            countryImage = itemView.findViewById(R.id.countryImage);
            countryName = itemView.findViewById(R.id.txtCountryNames);
           // totalCases = itemView.findViewById(R.id.txtTotalCases);

        }
    }
}
