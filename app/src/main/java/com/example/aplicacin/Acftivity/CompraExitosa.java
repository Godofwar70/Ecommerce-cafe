package com.example.aplicacin.Acftivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.aplicacin.R;
import com.example.aplicacin.model.MycartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompraExitosa extends AppCompatActivity {

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_exitosa);

        firestore = FirebaseFirestore.getInstance();
        List<MycartModel> list = (ArrayList<MycartModel>) getIntent().getSerializableExtra("itemList");

        if (list != null && list.size() > 0){
            for (MycartModel model : list){


                final HashMap<String, Object> cartMap = new HashMap<>();

                cartMap.put("productName",model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentDate",model.getCurrentDate());
                cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("Cantidad", String.valueOf(model.getCantidades()));
                cartMap.put("Totalprice",model.getTotalprice());
                firestore.collection("PedidoEcho").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(CompraExitosa.this, "Gracias por su pedido ", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }
}