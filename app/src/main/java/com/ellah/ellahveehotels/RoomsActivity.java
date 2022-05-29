package com.ellah.ellahveehotels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomsActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;

    private String [] rooms = {"GVH 201", "GVH 202", "GVH 203", "GVH 204", "GVH 205", "GVH 206", "GVH 307", "GVH 308", "GVH 109", "GVH 110"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rooms);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(RoomsActivity.this, "You Clicked " + rooms[position] + " Great Choice", Toast.LENGTH_SHORT).show();
            }
        });
    }
}