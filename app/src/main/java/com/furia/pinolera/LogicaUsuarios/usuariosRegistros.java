package com.furia.pinolera.LogicaUsuarios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.furia.pinolera.R;
import com.furia.pinolera.utilidades.Validar;
import com.furia.pinolera.utilidades.mensajes;
import com.furia.utils.Utils;
import static com.furia.pinolera.R.layout.activity_usuarios_registros;

public class usuariosRegistros extends AppCompatActivity {

    ImageView bgImageView;
    private EditText correoNewUser;
    private EditText passNewUser;
    private EditText passNewUser2;
    FirebaseUtilidades FU = new FirebaseUtilidades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_usuarios_registros);

        bgImageView = findViewById(R.id.bgImageView);
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);

        correoNewUser = findViewById(R.id.correoNewUser);
        passNewUser = findViewById(R.id.passNewUser);
        passNewUser2 = findViewById(R.id.passNewUser2);
    }

    public void registrarUsuario(View view) {
        String email = correoNewUser.getText().toString().trim();
        String password = passNewUser.getText().toString().trim();
        String password2 = passNewUser2.getText().toString().trim();

        if (Validar.camposVacios(email)) {
            mensajes.ToastMensaje(this,getString(R.string.errorCorreoVacio));
            correoNewUser.requestFocus();
            return;
        }

        if (Validar.camposVacios(password)) {
            mensajes.ToastMensaje(this,getString(R.string.errorPassVacio));
            passNewUser.requestFocus();
            return;
        }

        if (Validar.camposVacios(password2)) {
            mensajes.ToastMensaje(this,getString(R.string.passrepetirvacio));
            passNewUser2.requestFocus();
            return;
        }

        if (!Validar.validarCorreo(email)) {
            mensajes.ToastMensaje(this,getString(R.string.correoNovalido));
            correoNewUser.setText("");
            correoNewUser.requestFocus();
            return;
        }

        if(!Validar.passValidacion(password,password2)) {
            mensajes.ToastMensaje(this,getString(R.string.passNocumple));
            passNewUser2.setText("");
            passNewUser.setText("");
            passNewUser.requestFocus();
            return;
        }

        if(!password.equals(password2)) {
            mensajes.ToastMensaje(this,getString(R.string.passDistintas));
            passNewUser2.setText("");
            passNewUser.setText("");
            passNewUser.requestFocus();
            return;
        }

        FU.registroCorreoPass(email,password,this);
        limpiarEditTexts();
    }

    private void limpiarEditTexts() {
        correoNewUser.setText("");
        passNewUser.setText("");
        passNewUser2.setText("");
    }
}
