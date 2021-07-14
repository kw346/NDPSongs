package sg.edu.rp.c346.id20022735.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnin, btnshow;
    EditText etsong, etname, etyr;
    RadioGroup radgrp;
    RadioButton b1, b2, b3, b4, b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnin = findViewById(R.id.btnin);
        btnshow = findViewById(R.id.btnlist);
        etsong = findViewById(R.id.etst);
        etname = findViewById(R.id.etsn);
        etyr = findViewById(R.id.etyr);
        radgrp = findViewById(R.id.radgrperate);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);


        btnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etname.getText().toString().trim().length() != 0 && etsong.getText().toString().trim().length() != 0 && etyr.getText().toString().trim().length() != 0) {
                    int star = 0;
                    int checkedRadId = radgrp.getCheckedRadioButtonId();
                    if (checkedRadId == R.id.btn1) {
                        star = 1;
                    } else if (checkedRadId == R.id.btn2) {
                        star = 2;
                    } else if (checkedRadId == R.id.btn3) {
                        star = 3;
                    } else if (checkedRadId == R.id.btn4) {
                        star = 4;
                    } else if (checkedRadId == R.id.btn5) {
                        star = 5;
                    } else {
                        Toast.makeText(MainActivity.this, "Please rate the song!", Toast.LENGTH_SHORT).show();

                    }
                    String title = etsong.getText().toString();
                    String name = etname.getText().toString();
                    int yr = Integer.parseInt(etyr.getText().toString());

                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.insertSong(title, name, yr, star);
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(MainActivity.this, "Please fill everything", Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ViewList.class);
                startActivity(i);
            }
        });

    }
}