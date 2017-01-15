package com.example.naunem.toeictest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button start;
    Button about;
    Button quit;

    public void mapped() {
        start = (Button) findViewById(R.id.start);
        about = (Button) findViewById(R.id.about);
        quit = (Button) findViewById(R.id.quit);
        start.setOnClickListener(this);
        about.setOnClickListener(this);
        quit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mapped();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                Intent start = new Intent(MainActivity.this, ChoiceActivity.class);
                startActivity(start);
                break;
            case R.id.about:
                Intent about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.quit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Message");
                builder.setMessage("Do you want to exit ?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO: handle the OK
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }
    /*public void readData() throws IOException {
            String str="";
            StringBuffer buf = new StringBuffer();
            InputStream is = this.getResources().openRawResource(R.raw.questionreading);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is!=null) {
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n" );
                }
            }
            is.close();
            Toast.makeText(getBaseContext(),
                    buf.toString(), Toast.LENGTH_LONG).show();


        }*/


}
