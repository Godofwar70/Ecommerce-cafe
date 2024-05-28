package com.example.aplicacin.Acftivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aplicacin.R;
import com.example.aplicacin.model.Usemodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Spinner spine_provi,spine_city;
    AutoCompleteTextView provincia, ciudad;
    EditText nombre_t,apellido_t,cedula_t,correo_t,pass_t,telefono_t, direccion_t;
    RadioButton mas_t,fem_t;
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        nombre_t = findViewById(R.id.textNombre);
        apellido_t = findViewById(R.id.textApellidos);
        cedula_t = findViewById(R.id.textCorreo);
        correo_t = findViewById(R.id.textInputCorreo);
        pass_t = findViewById(R.id.textInputPass);
        telefono_t = findViewById(R.id.textTelefono);
        direccion_t = findViewById(R.id.textDireccion);
        ciudad = findViewById(R.id.textInputCiuda);
        provincia = findViewById(R.id.textInputProvin);


        String[] provinces = {"Seleccionar","Guayas","Santa elena","Azuay","Pichincha","Manabí"};
        String[] city = {"Seleccionar","Guayaquil","Olon","Cuenca","Quito","Portoviejo"};

        ArrayAdapter<String> province_t = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,provinces);
        AutoCompleteTextView autoCompleteTextView = provincia;
        autoCompleteTextView.setAdapter(province_t);

        ArrayAdapter<String> city_t = new ArrayAdapter<String >(this, android.R.layout.simple_spinner_item,city);
        AutoCompleteTextView autocomplete = ciudad;
        autocomplete.setAdapter(city_t);
    }


    public void agregarUsuario(View v){
        String nombre_tm = nombre_t.getText().toString();
        String apellido_tm = apellido_t.getText().toString();
        int cedula = Integer.parseInt(cedula_t.getText().toString());
        String correo_tm = correo_t.getText().toString();
        String pass_tm = pass_t.getText().toString();
        int telefono_tm = Integer.parseInt(telefono_t.getText().toString());
        String direccion_tm = direccion_t.getText().toString();
        String provincia_tm = provincia.getText().toString();
        String ciudad_tm = ciudad.getText().toString();

        mas_t = findViewById(R.id.radioMas);
        fem_t = findViewById(R.id.radioFem);
        boolean male = mas_t.isChecked();
        boolean feme = fem_t.isChecked();
        String genero = "";
        if(male == true){
            genero = "Maculino";
        }
        if (feme == true){
            genero   = "Femenino";
        }




        if (TextUtils.isEmpty(nombre_tm) && TextUtils.isEmpty(apellido_tm) && cedula == 0 && TextUtils.isEmpty(correo_tm)
                && TextUtils.isEmpty(pass_tm) && telefono_tm == 0 && TextUtils.isEmpty(direccion_tm)
                && TextUtils.isEmpty(provincia_tm) && TextUtils.isEmpty(ciudad_tm) ){
            Toast.makeText(v.getContext(), "llenar los campos", Toast.LENGTH_SHORT).show();

        }
        final String generoFinal = genero;
        auth.createUserWithEmailAndPassword(correo_tm,pass_tm)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Usemodel user = new Usemodel(apellido_tm, cedula, ciudad_tm, correo_tm,
                                    direccion_tm, generoFinal, nombre_tm, pass_tm, provincia_tm, telefono_tm);

                            myRef = FirebaseDatabase.getInstance().getReference("RegistrarUser");

                            String id = task.getResult().getUser().getUid();
                            myRef.child(id).setValue(user);

                            clear();

                            Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Register.this, "No se registro"+ task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
    public void clear(){
        nombre_t.clearFocus();
        nombre_t.setText("");
        apellido_t.setText("");
        cedula_t.setText("");
        correo_t.setText("");
        pass_t.setText("");
        telefono_t.setText("");
        direccion_t.setText("");

        provincia.setText("");
        ciudad.setText("");

    }

    public void regresarLogin(View v){
        Intent exit = new Intent(v.getContext(), Login.class);
        startActivity(exit);
        finish();
    }



}