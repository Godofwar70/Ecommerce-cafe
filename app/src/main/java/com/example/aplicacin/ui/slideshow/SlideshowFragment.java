package com.example.aplicacin.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacin.Acftivity.CompraExitosa;
import com.example.aplicacin.R;
import com.example.aplicacin.databinding.FragmentSlideshowBinding;
import com.example.aplicacin.model.MycartModel;
import com.example.aplicacin.model.adapter.MycartAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    MycartAdapter mycartAdapter;
    List<MycartModel>mycartModelList;
    FirebaseFirestore db;
    FirebaseAuth AUTH;
    RecyclerView recyclerView;
    TextView cartTotal;
    Button buyNow;

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        cartTotal = root.findViewById(R.id.textViewCart);
        db = FirebaseFirestore.getInstance();
        AUTH = FirebaseAuth.getInstance();
        recyclerView = root.findViewById(R.id.recicleCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mycartModelList = new ArrayList<>();
        mycartAdapter = new MycartAdapter(getActivity(),mycartModelList);
        recyclerView.setAdapter(mycartAdapter);

        db.collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        MycartModel mycartModel = documentSnapshot.toObject(MycartModel.class);
                        String documentId = documentSnapshot.getId();
                        mycartModel.setDocumentId(documentId);
                        mycartModelList.add(mycartModel);
                        mycartAdapter.notifyDataSetChanged();
                    }
                    double total = mycartAdapter.calculateTotal();
                    cartTotal.setText("Total: $" + total);
                }

            }
        });
        buyNow = root.findViewById(R.id.buttoncart);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CompraExitosa.class);
                intent.putExtra("itemList", (Serializable) mycartModelList);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}