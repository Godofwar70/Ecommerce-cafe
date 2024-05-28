package com.example.aplicacin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacin.model.category;
import com.example.aplicacin.model.adapter.categoryAdapter;

import com.example.aplicacin.R;
import com.example.aplicacin.databinding.FragmentHomeBinding;
import com.example.aplicacin.Acftivity.tipoProducto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements categoryAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    List<category> categoryList;
    categoryAdapter CategoryAdapter;
    FirebaseFirestore db;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        db =  FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.catgeoryReci);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryList = new ArrayList<>();
        CategoryAdapter = new categoryAdapter(getActivity(),categoryList);
        recyclerView.setAdapter(CategoryAdapter);

        CategoryAdapter.setOnItemClickListener(this);


        db.collection("Listaproducto").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                category Category = documentSnapshot.toObject(category.class);
                                categoryList.add(Category);
                                Log.d("TipoProducto", "Producto: " + categoryList.toString());
                                CategoryAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Log.e("Firestore", "Error al obtener documentos", task.getException());
                            Toast.makeText(getActivity(),"Error" + task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onItemClick(category item) {
        String type = item.getType();
        // Abre la actividad tipoProducto con el tipo como extra
        Intent intent = new Intent(getActivity(), tipoProducto.class);
        intent.putExtra("type", type);
        startActivity(intent);

    }
}