package com.example.mycontacts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.R;
import com.example.mycontacts.models.ModelContacts;

import java.util.List;

public class FavRvAdapter extends RecyclerView.Adapter<FavRvAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ModelContacts> listContacts;

    public FavRvAdapter(Context context, List<ModelContacts> listContacts) {
        this.context = context;
        this.listContacts = listContacts;
    }

    @NonNull
    @Override
    public FavRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_fav,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavRvAdapter.ViewHolder holder, int position) {

        TextView fav_name , fav_number;

        fav_name= holder.fav_name;
        fav_number= holder.fav_number;
        if (listContacts.get(position).getStar() == true){
            fav_name.setText(listContacts.get(position).getName());
            fav_number.setText(listContacts.get(position).getNumber());
        }else {

        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        for (int i=0 ; i < listContacts.size() ; i++){
            if (listContacts.get(i).getStar() == true){
                size++;
            }
        }
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fav_name,fav_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fav_name = itemView.findViewById(R.id.fav_name);
            fav_number = itemView.findViewById(R.id.fav_number);

        }
    }
}
