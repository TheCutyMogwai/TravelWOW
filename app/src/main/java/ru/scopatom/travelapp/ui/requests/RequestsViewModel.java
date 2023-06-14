package ru.scopatom.travelapp.ui.requests;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import ru.scopatom.travelapp.R;

public class RequestsViewModel extends RecyclerView.ViewHolder {
    View requestView;

    public RequestsViewModel(@NonNull View itemView) {
        super(itemView);
        requestView = itemView;
    }

    public void setDetails(Context context, String client, String description, boolean done, String tour) {
        TextView clientName = requestView.findViewById(R.id.clientName);
        TextView tourName = requestView.findViewById(R.id.tourName);
        TextView descriptionText = requestView.findViewById(R.id.description);
        CheckBox requestDone = requestView.findViewById(R.id.requestDone);
        requestDone.setOnClickListener(view -> {
            if (view.getId() == R.id.requestDone) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://travelapp-87f1a-default-rtdb.europe-west1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("requests/0/done");
                myRef.setValue(!done);
            }
        });
        clientName.setText(client);
        descriptionText.setText(description);
        tourName.setText(tour);
        requestDone.setChecked(done);
    }
}