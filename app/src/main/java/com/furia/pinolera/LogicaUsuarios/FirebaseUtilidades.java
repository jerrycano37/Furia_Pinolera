package com.furia.pinolera.LogicaUsuarios;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.furia.pinolera.R;
import com.furia.pinolera.menuPrincipal;
import com.furia.pinolera.utilidades.mensajes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class FirebaseUtilidades {
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    void registroCorreoPass(String correo, String password, Context ctx) {
        FirebaseApp.initializeApp(ctx);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usuarioActual = mAuth.getCurrentUser();
        barraEspera(ctx, ctx.getString(R.string.Registrandowait), ctx.getString(R.string.esperePorFavor));

        mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener((Activity) ctx, task -> {
            if (task.isSuccessful()) {
                Objects.requireNonNull(usuarioActual).sendEmailVerification();
                progressDialog.dismiss();
                Log.d(TAG, "createUserWithEmail:success");
                mensajes.alertaCierraActivity(ctx, ctx.getString(R.string.RegistroCorrecto), ctx.getString(R.string.RegistradoCorrectamente));
            } else {
                progressDialog.dismiss();
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                mensajes.alertacomun(ctx, ctx.getString(R.string.RegistroError), ctx.getString(R.string.RegistradoError));
            }
        });
    }

    public void inicioSesion(String correo, String password, Context ctx) {
        FirebaseApp.initializeApp(ctx);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        barraEspera(ctx, ctx.getString(R.string.entrandoApp), ctx.getString(R.string.esperePorFavor));

        mAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener((Activity) ctx, task -> {
            if (task.isSuccessful()) {
                Boolean estatus = Objects.requireNonNull(user).isEmailVerified();

                if (estatus.equals(false)) {
                    progressDialog.dismiss();
                    mensajes.alertacomun(ctx, "Advertencia", "No has verificado tu correo electronico");
                } else {
                    progressDialog.dismiss();
                    Log.d(TAG, "signInWithEmail:success");
                    Intent intenMenu = new Intent(ctx, menuPrincipal.class);
                    ctx.startActivity(intenMenu);
                }
            } else {
                progressDialog.dismiss();
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                mensajes.alertacomun(ctx, ctx.getString(R.string.SesionError), ctx.getString(R.string.msjErrorSesion));
            }
        });
    }

    public void restablecerPass(Context ctx, String correo) {
        barraEspera(ctx, ctx.getString(R.string.EnviandoSoli), ctx.getString(R.string.esperePorFavor));

        FirebaseAuth.getInstance().sendPasswordResetEmail(correo).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                Log.d(TAG, "Correo para restablecer contraseña enviado");
            }
        });
    }

    private void barraEspera(Context ctx, String titulo, String mensaje) {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage(mensaje);
        progressDialog.setTitle(titulo);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
}
