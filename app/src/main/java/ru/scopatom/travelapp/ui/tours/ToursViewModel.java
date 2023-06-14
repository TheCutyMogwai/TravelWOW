package ru.scopatom.travelapp.ui.tours;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.scopatom.travelapp.R;

public class ToursViewModel extends RecyclerView.ViewHolder {
    View tourView;

    public ToursViewModel(@NonNull View itemView) {
        super(itemView);
        tourView = itemView;
    }

    public void setDetails(Context context, String image, String name, String days, String price) {
        TextView tourName = tourView.findViewById(R.id.tourName);
        TextView tourDays = tourView.findViewById(R.id.tourDays);
        TextView tourPrice = tourView.findViewById(R.id.tourPrice);
        ImageView tourImage = tourView.findViewById(R.id.tourImage);
        tourName.setText(name);
        tourDays.setText(days);
        tourPrice.setText(price);
        Picasso.get().load(image).into(tourImage);
    }
}