package ru.scopatom.travelapp.ui.clients;

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

public class ClientsFragment extends Fragment {

    private RecyclerView clientsRecyclerView;
    private DatabaseReference clientsRef;
    private FirebaseRecyclerAdapter<ClientModel, ClientsViewModel> adapter;
    private FirebaseRecyclerOptions<ClientModel> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_clients, container, false);

        clientsRecyclerView = root.findViewById(R.id.clientsRecyclerView);

        clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseDatabase clientsFirebaseDatabase = FirebaseDatabase.getInstance("https://travelapp-87f1a-default-rtdb.europe-west1.firebasedatabase.app/");
        clientsRef = clientsFirebaseDatabase.getReference("clients");

        showClients();

        return root;
    }

    private void showClients() {
        options = new FirebaseRecyclerOptions.Builder<ClientModel>().setQuery(clientsRef, ClientModel.class).build();
        adapter = new FirebaseRecyclerAdapter<ClientModel, ClientsViewModel>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClientsViewModel viewHolder, int i, @NonNull ClientModel model) {
                viewHolder.setDetails(getContext(), model.getImage(), model.getName(), model.getPhone());
            }

            @NonNull
            @Override
            public ClientsViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_row, parent, false);
                return new ClientsViewModel(itemView);
            }
        };

        clientsRecyclerView.setAdapter(adapter);
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