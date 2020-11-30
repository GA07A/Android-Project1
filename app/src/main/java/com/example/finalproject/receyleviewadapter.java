package com.example.finalproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class receyleviewadapter extends RecyclerView.Adapter<receyleviewadapter.Homeviewholder>  {

    ArrayList<item> show;
    public receyleviewadapter(ArrayList<item> items) {
        this.show = items;
    }


    @NonNull
    @Override
    public Homeviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.custem,null,false );
        Homeviewholder homeviewholder=new Homeviewholder( view);
        return homeviewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull Homeviewholder holder, int position) {
        if (holder instanceof Homeviewholder){
            final item items=show.get( position );
            final Homeviewholder homeviewholder=(Homeviewholder) holder;
            homeviewholder.name.setText( items.getName() );
            homeviewholder.taitle.setText( items.getTital() );
            homeviewholder.image.setImageResource(  items.getImage()  );
        }
    }

    @Override
    public int getItemCount() {
        return show.size();
    }


    public  void filterlist(ArrayList<item> filteredlist){
        show =filteredlist;
        notifyDataSetChanged();
    }
    class Homeviewholder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,taitle;
        public Homeviewholder(@NonNull View itemView) {
            super( itemView );
            name=itemView.findViewById( R.id.name_show );
            taitle=itemView.findViewById( R.id.name2_show );
            image=itemView.findViewById( R.id.image_show );

        }
    }}