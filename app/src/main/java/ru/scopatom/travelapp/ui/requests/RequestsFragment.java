package ru.scopatom.travelapp.ui.requests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.scopatom.travelapp.R;

public class RequestsFragment extends Fragment {

    private RecyclerView requestsRecyclerView;
    private DatabaseReference requestsRef;
    private FirebaseRecyclerAdapter<RequestModel, RequestsViewModel> adapter;
    private FirebaseRecyclerOptions<RequestModel> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_requests, container, false);

        requestsRecyclerView = root.findViewById(R.id.requestsRecyclerView);

        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseDatabase clientsFirebaseDatabase = FirebaseDatabase.getInstance("https://travelapp-87f1a-default-rtdb.europe-west1.firebasedatabase.app/");
        requestsRef = clientsFirebaseDatabase.getReference("requests");

        showRequests();

        return root;
    }

    private void showRequests() {
        options = new FirebaseRecyclerOptions.Builder<RequestModel>().setQuery(requestsRef, RequestModel.class).build();
        adapter = new FirebaseRecyclerAdapter<RequestModel, RequestsViewModel>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestsViewModel viewHolder, int i, @NonNull RequestModel model) {
                viewHolder.setDetails(getContext(), model.getClient(), model.getDescription(), model.isDone(), model.getTour());
            }

            @NonNull
            @Override
            public RequestsViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_row, parent, false);
                return new RequestsViewModel(itemView);
            }
        };

        requestsRecyclerView.setAdapter(adapter);
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