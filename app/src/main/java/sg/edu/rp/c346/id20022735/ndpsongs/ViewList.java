package sg.edu.rp.c346.id20022735.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewList extends AppCompatActivity {

    ListView lvall;
    Button btn;
    ArrayList<Song> sl;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        lvall = findViewById(R.id.allsong);
        btn  =findViewById(R.id.button);

        Intent intrec = getIntent();

        sl = new ArrayList<Song>();
        aa = new ArrayAdapter(ViewList.this
                , android.R.layout.simple_list_item_1, sl);
        lvall.setAdapter(aa);


        lvall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = sl.get(position);
                Intent i = new Intent(ViewList.this,
                        EditList.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });



    }
    @Override
    protected void onResume(){
        super.onResume();
        DBHelper db = new DBHelper(ViewList.this);
        sl.clear();
        sl.addAll(db.getAllSongs());
        aa.notifyDataSetChanged();

    }
}