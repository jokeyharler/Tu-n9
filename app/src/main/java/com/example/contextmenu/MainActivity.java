package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrCourse;
    ListView lvmonhoc;
    EditText editText;
    int vitri = -1;
    Button btadd,btCapNhat,btdel;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvmonhoc = (ListView) findViewById(R.id.Course);
        arrCourse = new ArrayList<>();
        arrCourse.add("JavaScript");
        arrCourse.add("PHP");
        arrCourse.add("HTML");
        arrCourse.add("XML");
        final ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrCourse);
        lvmonhoc.setAdapter(arrayAdapter);
        editText = (EditText) findViewById(R.id.plaintext);
        btadd = (Button) findViewById(R.id.btadd);
        btCapNhat = (Button) findViewById(R.id.btedit);
        btdel = (Button) findViewById(R.id.btdel);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monhoc = editText.getText().toString();
                arrCourse.add(monhoc);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        lvmonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(arrCourse.get(position));
                vitri = position;
            }
        });
        btCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrCourse.set(vitri,editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });
        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrCourse.remove(vitri);
                arrayAdapter.notifyDataSetChanged();
            }
        });
//        lvmonhoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
////            @Override
////            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
////                arrCourse.remove(position);
////                arrayAdapter.notifyDataSetChanged();
////                return false;
////            }
////        });
        registerForContextMenu(lvmonhoc);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}