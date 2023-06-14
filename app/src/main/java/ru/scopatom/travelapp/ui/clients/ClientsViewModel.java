package ru.scopatom.travelapp.ui.clients;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.scopatom.travelapp.R;

public class ClientsViewModel extends RecyclerView.ViewHolder {
    View clientView;

    public ClientsViewModel(@NonNull View itemView) {
        super(itemView);
        clientView = itemView;
    }

    public void setDetails(Context context, String image, String name, String phone) {
        TextView clientName = clientView.findViewById(R.id.clientName);
        TextView clientPhone = clientView.findViewById(R.id.clientPhone);
        ImageView clientImage = clientView.findViewById(R.id.clientImage);
        clientName.setText(name);
        clientPhone.setText(phone);
        Picasso.get().load(image).into(clientImage);
    }
}