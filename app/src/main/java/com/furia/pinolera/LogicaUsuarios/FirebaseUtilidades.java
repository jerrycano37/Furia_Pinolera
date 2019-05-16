package com.furia.pinolera.LogicaUsuarios;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import com.furia.pinolera.R;
import com.furia.pinolera.menuPrincipal;
import com.furia.pinolera.utilidades.mensajes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import static android.content.ContentValues.TAG;

public class FirebaseUtilidades {
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    void registroCorreoPass(String correo, String password, Context ctx) {
        FirebaseApp.initializeApp(ctx);
        mAuth = FirebaseAuth.getInstance();
        barraEspera(ctx,ctx.getString(R.string.Registrandowait),ctx.getString(R.string.esperePorFavor));

        mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener((Activity) ctx, task -> {
            if (task.isSuccessful()) {
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
        barraEspera(ctx,ctx.getString(R.string.entrandoApp),ctx.getString(R.string.esperePorFavor));

        mAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener((Activity) ctx, task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                Log.d(TAG, "signInWithEmail:success");
                Intent intenMenu = new Intent(ctx, menuPrincipal.class);
                ctx.startActivity(intenMenu);
            } else {
                progressDialog.dismiss();
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                mensajes.alertacomun(ctx, ctx.getString(R.string.SesionError), ctx.getString(R.string.msjErrorSesion));
            }
        });
    }

    public void restablecerPass(Context ctx, String correo) {

        FirebaseAuth.getInstance().sendPasswordResetEmail(correo).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                Log.d(TAG, "Email sent.");

            }
        });

    }

    private void barraEspera(Context ctx,String titulo,String mensaje) {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage(mensaje);
        progressDialog.setTitle(titulo);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
}
