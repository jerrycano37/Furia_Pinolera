package com.furia.pinolera.utilidades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class mensajes {

    public static void alertacomun(Context ctx, String title, String message) {
        new AlertDialog.Builder(ctx).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(
                "ok", (dialog, which) -> { }).show();
    }

    public static void ToastMensaje(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    public static void alertaCierraActivity(Context ctx, String title, String message) {
        new AlertDialog.Builder(ctx).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(
                "ok", (dialog, which) -> ((Activity) ctx).finish()).show();
    }
}
