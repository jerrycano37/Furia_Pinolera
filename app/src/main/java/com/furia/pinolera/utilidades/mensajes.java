package com.furia.pinolera.utilidades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.furia.pinolera.LogicaUsuarios.FirebaseUtilidades;
import com.furia.pinolera.R;

public class mensajes {

    public static void alertacomun(Context ctx, String title, String message) {
        new AlertDialog.Builder(ctx).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(
                "ok", (dialog, which) -> {
                }).show();
    }

    public static void ToastMensaje(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    public static void alertaCierraActivity(Context ctx, String title, String message) {
        new AlertDialog.Builder(ctx).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(
                "ok", (dialog, which) -> ((Activity) ctx).finish()).show();
    }

    public static void alertaPassRecovery(Context ctx, String title, String message) {
        FirebaseUtilidades FU = new FirebaseUtilidades();
        EditText taskEditText = new EditText(ctx);
        AlertDialog dialog = new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)
                .setView(taskEditText)
                .setPositiveButton("Enviar", (dialog1, which) -> {
                    alertacomun(ctx, ctx.getString(R.string.EnviadoOk), ctx.getString(R.string.msjRestauranPass));
                    FU.restablecerPass(ctx, taskEditText.getText().toString());
                })
                .setNegativeButton("Regresar", null)
                .create();
        dialog.show();
    }
}
