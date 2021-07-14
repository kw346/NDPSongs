package sg.edu.rp.c346.id20022735.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditList extends AppCompatActivity {

    Button bcan, bup, bdel;
    EditText etid,etsong, etsinger, etyr;
    RadioGroup ratings;
    RadioButton b1 ,b2, b3 ,b4, b5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        bcan = findViewById(R.id.btncan);
        bup= findViewById(R.id.btnup);
        bdel =findViewById(R.id.btndel);

        etid = findViewById(R.id.etid);
        etsong =  findViewById(R.id.etsong);
        etsinger = findViewById(R.id.etname);
        etyr =findViewById(R.id.etyr);

        ratings = findViewById(R.id.radrate);
        b1 =findViewById(R.id.btn1);
        b2 =findViewById(R.id.btn2);
        b3 =findViewById(R.id.btn3);
        b4 =findViewById(R.id.btn4);
        b5 =findViewById(R.id.btn5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etid.setText(data.get_id());
        etsong.setText(data.getTitle());
        etsinger.setText(data.getSingers());
        etyr.setText(data.getYear());

        bup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditList.this);
                data.setTitle(etsong.getText().toString());
                data.setSingers(etsinger.getText().toString());
                int year = Integer.parseInt(etyr.getText().toString());
                data.setYear(year);
                data.setSingers(etsinger.getText().toString());
                int star = 0;
                int checkedRadId = ratings.getCheckedRadioButtonId();
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
                }
                data.setStars(star);
                dbh.updateSongs(data);
                dbh.close();
            }
        });
        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditList.this);
                dbh.deleteSong(data.get_id());

            }
        });


    }
}