package com.example.aplicacin.Acftivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacin.MainActivity;
import com.example.aplicacin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {


    EditText correo_t,pass_t;
    FirebaseAuth auth;
    CheckBox inicio;

    SharedPreferences save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        correo_t = findViewById(R.id.textCorreo);
        pass_t = findViewById(R.id.textPass);
        inicio = findViewById(R.id.CheckSesion);

        save = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        boolean checktrue = save.getBoolean("checktrue",false);
        if (checktrue){
            String correo_mp = save.getString("email","");
            String pass_mp = save.getString("pass","");

            correo_t.setText(correo_mp);
            pass_t.setText(pass_mp);
            inicio.setChecked(true);
        }
    }
    public void singUp(View v){
        Intent register = new Intent(v.getContext(), Register.class);
        startActivity(register);
        finish();
    }



    public void Logear(View v){
        String correo_tm = correo_t.getText().toString();
        String pass_tm = pass_t.getText().toString();

        if (TextUtils.isEmpty(correo_tm) && TextUtils.isEmpty(pass_tm)) {

            Toast.makeText(v.getContext(), "Debe llenar ambos campos", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(correo_tm,pass_tm)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (inicio.isChecked()){
                                SharedPreferences.Editor edit = save.edit();
                                edit.putString("email",correo_tm);
                                edit.putString("pass",pass_tm);
                                edit.putBoolean("checktrue",true);
                                edit.apply();
                                Toast.makeText(Login.this, "Se ha guardado", Toast.LENGTH_SHORT).show();
                            }
                            Intent login = new Intent(v.getContext(), MainActivity.class);
                            startActivity(login);
                            finish();

                            Toast.makeText(v.getContext(),"Inicio de sesión exitosa",Toast.LENGTH_LONG).show();
                        }
                        else {
                            // Log para registrar un error en el inicio de sesión
                            Log.e("LoginError", "Error en el inicio de sesión", task.getException());
                            // Puedes mostrar un Toast para informar al usuario sobre el error
                            Toast.makeText(Login.this, "Error en el inicio de sesión"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });



    }
}