package com.luan.session9_view.session9view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder>{
    ArrayList<ModelUser> users;
    LayoutInflater layoutInflater;
    Context context;
    MyItemClickListerner myItemClickListerner;

    public AdapterUsers(Context context,ArrayList<ModelUser> users) {
        this.users = users;
        this.context=context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = layoutInflater.inflate(R.layout.user_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final ModelUser user = users.get(i);
        myViewHolder.tvAddress.setText(user.getAddress());
        myViewHolder.tvName.setText(user.getName());
        myViewHolder.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showMenu()
                view.setTag(user);
                showMenu(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    interface MyItemClickListerner{
        void onItemClick(View v, int i);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName,tvAddress;
        ImageButton btnMenu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tvName);
            tvAddress=(TextView)itemView.findViewById(R.id.tvAddress);
            btnMenu=(ImageButton)itemView.findViewById(R.id.btnMenu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.e("CLICKABLE","CLICK");
            if(myItemClickListerner!=null) {
                myItemClickListerner.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setOnItemClickLister(MyItemClickListerner click){
        this.myItemClickListerner=click;
    }

    public void showMenu(View v){
        final ModelUser user = (ModelUser) v.getTag();
        PopupMenu popupMenu = new PopupMenu(context,v);
        popupMenu.inflate(R.menu.main);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.itemShare:
                        Log.i("SHARE",user.getName());
                        break;

                    case R.id.itemDelete:
                        Log.i("DELETE",user.getName());
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
