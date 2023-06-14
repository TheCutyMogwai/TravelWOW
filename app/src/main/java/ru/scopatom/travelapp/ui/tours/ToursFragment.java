package ru.scopatom.travelapp.ui.tours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.scopatom.travelapp.R;

public class ToursFragment extends Fragment {

    private RecyclerView toursRecyclerView;
    private DatabaseReference toursRef;
    private FirebaseRecyclerAdapter<TourModel, ToursViewModel> adapter;
    private FirebaseRecyclerOptions<TourModel> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tours, container, false);

        toursRecyclerView = root.findViewById(R.id.toursRecyclerView);

        toursRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseDatabase clientsFirebaseDatabase = FirebaseDatabase.getInstance("https://travelapp-87f1a-default-rtdb.europe-west1.firebasedatabase.app/");
        toursRef = clientsFirebaseDatabase.getReference("tours");

        showTours();

        return root;
    }

    private void showTours() {
        options = new FirebaseRecyclerOptions.Builder<TourModel>().setQuery(toursRef, TourModel.class).build();
        adapter = new FirebaseRecyclerAdapter<TourModel, ToursViewModel>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ToursViewModel viewHolder, int i, @NonNull TourModel model) {
                viewHolder.setDetails(getContext(), model.getImage(), model.getName(), model.getDays(), model.getPrice());
            }

            @NonNull
            @Override
            public ToursViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_row, parent, false);
                return new ToursViewModel(itemView);
            }
        };

        toursRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}