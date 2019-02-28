package com.luan.session9_view.session9view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcView;
    ArrayList<ModelUser> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcView=(RecyclerView) findViewById(R.id.rcView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
        //LinearLayoutManager (vertical, horizontal)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        rcView.setLayoutManager(linearLayoutManager);
        AdapterUsers adapterUsers = new AdapterUsers(MainActivity.this,users);
        adapterUsers.setOnItemClickLister(new AdapterUsers.MyItemClickListerner() {
            @Override
            public void onItemClick(View v, int i) {
                Log.i("USER",users.get(i).getName() + users.get(i).getAddress());
            }
        });
        rcView.setAdapter(adapterUsers);
        loadData();
        adapterUsers.notifyDataSetChanged();
    }

    void loadData(){
        for (int i = 0; i < 50 ; i++) {
            ModelUser user = new ModelUser();
            user.setName("name "+i);
            user.setAddress("address "+i);
            users.add(user);
        }

        //Button button = new Button();
        //registerForContextMenu(button);
    }


//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuInflater menuInflater =getMenuInflater();
//        menuInflater.inflate(R.menu.main,menu);
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemAdd:
                Toast.makeText(MainActivity.this,"Add",Toast.LENGTH_SHORT).show();
                break;

            case R.id.itemDelete:
                Toast.makeText(MainActivity.this,"DELETE",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
}
