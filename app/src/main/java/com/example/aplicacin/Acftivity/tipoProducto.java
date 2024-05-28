package com.example.aplicacin.Acftivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.aplicacin.R;
import com.example.aplicacin.model.producto;
import com.example.aplicacin.model.adapter.productoAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class tipoProducto extends AppCompatActivity {
    RecyclerView recyclerView;
    List<producto> productoList;
    productoAdapter ProductAdap;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_producto);

        db = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.tipoItem);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productoList = new ArrayList<>();
        ProductAdap = new productoAdapter(this, productoList);
        recyclerView.setAdapter(ProductAdap);

        if (type != null) {
            // Consulta los productos con el tipo específico
            Query query = db.collection("Allproduct").whereEqualTo("type", type);

            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            producto product = documentSnapshot.toObject(producto.class);
                            productoList.add(product);
                            ProductAdap.notifyDataSetChanged();
                        }
                    } else {
                        // Manejar el error
                        Log.e("Firestore", "Error al obtener productos", task.getException());
                        Toast.makeText(tipoProducto.this, "Error al obtener", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        /*String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.tipoItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productoList = new ArrayList<>();
        ProductAdap = new productoAdapter(this,productoList);
        recyclerView.setAdapter(ProductAdap);


        if (type != null && type.equalsIgnoreCase("postrechocolate")){

            db.collection("Allproduct").whereEqualTo("type","postrechocolate").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                producto Product = documentSnapshot.toObject(producto.class);
                                productoList.add(Product);
                                ProductAdap.notifyDataSetChanged();
                            }

                        }
                    });

        }*/



    }


}
