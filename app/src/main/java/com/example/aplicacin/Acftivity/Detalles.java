package com.example.aplicacin.Acftivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.aplicacin.R;
import com.example.aplicacin.model.producto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Detalles extends AppCompatActivity {

    int value = 1;
    int TotalPresio = 0;
    Button butIncrem, butDescre,addcart;
    TextView valor,descript,pres;
    RatingBar rating;
    ImageView detailIm;
    producto Product = null;
    Toolbar bals;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button itemdel;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        valor = findViewById(R.id.textCantidad);

        butIncrem = findViewById(R.id.butoIncrementar);
        butIncrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value<30) {
                    value++;
                    valor.setText(String.valueOf(value));
                }
            }
        });
        butDescre = findViewById(R.id.butoDescrementar);
        butDescre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value >0) {
                    value--;
                    valor.setText(String.valueOf(value));
                }
            }
        });

        descript = findViewById(R.id.detailDescrip);
        pres = findViewById(R.id.detailPrecio);
        detailIm = findViewById(R.id.detailImage);
        rating = findViewById(R.id.detailEstrella);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("image_Url");
        float price = intent.getFloatExtra("Price", 0.0f);
        String description = intent.getStringExtra("Description");
        float ratingValue = intent.getFloatExtra("Estrella", 0.0f);


        // Mostrar datos en la interfaz
        Glide.with(getApplicationContext()).load(imageUrl).into(detailIm);
        rating.setRating(ratingValue);
        descript.setText(description);
        pres.setText("Price: $" + price);

        addcart = findViewById(R.id.cartIte);
        
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = auth.getCurrentUser();
                if (currentUser != null) {
                    // Hay un usuario autenticado, puedes acceder a su UID
                    String userId = currentUser.getUid();
                    addCartItem(userId);
                } else {
                    // No hay un usuario autenticado, agrega el elemento al carrito sin usar un UID
                    addCartItem(null);
                }
            }
            
        });


    }

    private void addCartItem(String userId) {
        Intent intent = getIntent();

        String Productos = intent.getStringExtra("Producto");

        String priceText = pres.getText().toString();
        float price = Float.parseFloat(priceText.replaceAll("[^0-9.]", ""));
        int totalquanty = Integer.parseInt(valor.getText().toString());
        float totales = price * totalquanty;

        String saveCurrentDate,saveCurrentTime;
        Calendar calforce = Calendar.getInstance();

        SimpleDateFormat currentDate= new SimpleDateFormat("MM,dd,yyyy");
        saveCurrentDate = currentDate.format(calforce.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calforce.getTime());

        final HashMap<String,String>cartMap = new HashMap<>();

        cartMap.put("productName",Productos);
        cartMap.put("productPrice",String.valueOf(price));
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("Cantidades",String.valueOf(totalquanty));
        cartMap.put("Totalprice",String.valueOf(totales));

        /*firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(Detalles.this,"Add to a cart ",Toast.LENGTH_SHORT).show();
                    }
                });*/

        if (userId != null) {
            // Si hay un usuario autenticado, agrega el carrito al subconjunto "CurrentUser" del usuario
            firestore.collection("AddToCart").document(userId)
                    .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Detalles.this, "Add to a cart ", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Si no hay un usuario autenticado, agrega el carrito sin asociarlo a un usuario específico
            firestore.collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(Detalles.this, "Add to a cart ", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


}