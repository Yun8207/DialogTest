package tw.alex.dialogtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private Timer timer;
    private int choice;
    private boolean[] choValue = new boolean[]{true,false,false};
    private String[] choices = new String[]{"iOS", "Android", "Windows"};

    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer();
        mainView = findViewById(R.id.mainView);

    }

    public void test1(View view) {
        timer.schedule(new CloseAlertTask(), 3*1000);

        AlertDialog.Builder builder =
        new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setCancelable(false);
        alertDialog = builder.create();

        alertDialog.show();
    }




    private class CloseAlertTask extends TimerTask{
        @Override
        public void run() {
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
        }
    }

    public void test2(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("Enter Account")
                        .setCancelable(false);
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = input.getText().toString();
                Log.v("alex", str);
            }
        });
        builder.setNegativeButton("Cancel",null);
        alertDialog = builder.create();

        alertDialog.show();


    }

    public void test3(View view) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("I Am Title")
                        .setIcon(R.drawable.ic_assignment_black_24dp)
                        .setCancelable(false);
//        builder.setItems(new String[]{"Android", "iOS", "Windows"}, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Log.v("alex", "Which = " + which);
//            }
//        });
        builder.setSingleChoiceItems(new String[]{"Android", "iOS", "Windows"}, 0, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("alex", "Which = " + which);
                choice = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("alex", ""+ choice);
            }
        });

        alertDialog = builder.create();

        alertDialog.show();

    }

    public void test4(View view) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("I Am Title")
                        .setIcon(R.drawable.ic_assignment_black_24dp)
                        .setCancelable(false);
        builder.setMultiChoiceItems(choices, choValue,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        choValue[which] = isChecked;
                    }
                });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0; i<choValue.length; i++){
                    Log.v("alex",  ""+choices[i] +" : " + choValue[i]);
                }


            }
        });

        alertDialog = builder.create();

        alertDialog.show();


    }

    public void test5(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Title");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.mydialog);

        EditText account = dialog.findViewById(R.id.dialog_account);
        EditText passwd = dialog.findViewById(R.id.dialog_passwd);
        Button ok = dialog.findViewById(R.id.dialog_ok);
        Button cancel = dialog.findViewById(R.id.dialog_cancel);

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public void test6(View view) {

//        Toast t = Toast.makeText(this,"Hello, World", Toast.LENGTH_SHORT);
//        t.setGravity(Gravity.FILL_HORIZONTAL+Gravity.CENTER_VERTICAL, 0 , 200);
//        t.show();

        showMyToast("fail", false);
    }

    private void showMyToast(String mesg, boolean isSuccess){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.mytoast,(ViewGroup)findViewById(R.id.toast_vg));

        TextView toastMesg = view.findViewById(R.id.toast_title);
        toastMesg.setText(mesg);
        ImageView img = view.findViewById(R.id.toast_img);
        img.setImageResource(isSuccess?R.drawable.ic_lens_black_24dp:R.drawable.ic_lens_black_24dp);//change img

        Toast t2 = new Toast(this);
        t2.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t2.setDuration(Toast.LENGTH_LONG);
        t2.setView(view);
        t2.show();
    }

//    public void test7(View view) {
//
//        Snackbar.make(mainView, "Hello, Ok", Snackbar.LENGTH_SHORT)
//                .setAction("Ok", new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        Log.v("alex", "OK");
//                    }
//                })
//                .show();
//
//
//
//    }

}
