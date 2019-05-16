package com.furia.pinolera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.furia.pinolera.LogicaUsuarios.FirebaseUtilidades;
import com.furia.pinolera.LogicaUsuarios.usuariosRegistros;
import com.furia.pinolera.utilidades.Validar;
import com.furia.pinolera.utilidades.mensajes;
import com.furia.utils.Utils;

public class MainActivity extends AppCompatActivity {

    TextView nuevoUser;
    LinearLayout facebookLinearLayout, twitterLinearLayout, googlePlusLinearLayout;
    Button loginButton, forgotButton;
    ImageView bgImageView;
    private EditText correoLogin;
    private EditText passCorreo;
    FirebaseUtilidades FU = new FirebaseUtilidades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginmenu);

        correoLogin = findViewById(R.id.correoLogin);
        passCorreo = findViewById(R.id.passCorreo);

        initUI();
        initDataBindings();
        initActions();

    }

    private void initUI() {
        forgotButton = findViewById(R.id.forgotButton);
        nuevoUser = findViewById(R.id.nuevoUsuario);
        facebookLinearLayout = findViewById(R.id.facebookLinearLayout);
        twitterLinearLayout = findViewById(R.id.twitterLinearLayout);
        googlePlusLinearLayout = findViewById(R.id.googlePlusLinearLayout);
        loginButton = findViewById(R.id.loginButton);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {

        nuevoUser.setOnClickListener(view -> {
            Intent intentNewUser = new Intent(this, usuariosRegistros.class);
            startActivity(intentNewUser);
        });

        loginButton.setOnClickListener(view -> {

            String email = correoLogin.getText().toString().trim();
            String password = passCorreo.getText().toString().trim();

            if (Validar.camposVacios(email)) {
                mensajes.ToastMensaje(this,getString(R.string.errorCorreoVacio));
                return;
            }

            if (Validar.camposVacios(password)) {
                mensajes.ToastMensaje(this,getString(R.string.errorPassVacio));
                return;
            }

            FU.inicioSesion(email,password,this);
            limpiarEditTexts();

        });

    }

    private void limpiarEditTexts() {
        correoLogin.setText("");
        passCorreo.setText("");
    }
}
