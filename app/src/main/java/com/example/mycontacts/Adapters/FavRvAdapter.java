package com.example.mycontacts.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private OnBtnCallFavListener onBtnCallFavListener;
    private OnBtnStarFavListener onBtnStarFavListener;

    public FavRvAdapter(Context context, List<ModelContacts> listContacts, OnBtnCallFavListener onBtnCallFavListener, OnBtnStarFavListener onBtnStarFavListener) {
        this.context = context;
        this.listContacts = listContacts;
        this.onBtnCallFavListener = onBtnCallFavListener;
        this.onBtnStarFavListener = onBtnStarFavListener;
    }

    @NonNull
    @Override
    public FavRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_fav,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,onBtnCallFavListener,onBtnStarFavListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavRvAdapter.ViewHolder holder, int position ) {

        TextView fav_name , fav_number;
        fav_name= holder.fav_name;
        fav_number= holder.fav_number;

        Log.d("TAG", "position ::" + position);
        fav_name.setText(listContacts.get(position).getName());
        fav_number.setText(listContacts.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fav_name,fav_number;
        OnBtnCallFavListener onBtnCallFavListener;
        OnBtnStarFavListener onBtnStarFavListener;
        Button call;
        Button star;

        public ViewHolder(@NonNull View itemView, final OnBtnCallFavListener onBtnCallFavListener , final OnBtnStarFavListener onBtnStarFavListener) {
            super(itemView);

            fav_name = itemView.findViewById(R.id.fav_name);
            fav_number = itemView.findViewById(R.id.fav_number);
            call = itemView.findViewById(R.id.btn_call_fav);
            star = itemView.findViewById(R.id.btn_star_fav);

            this.onBtnCallFavListener = onBtnCallFavListener;
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBtnCallFavListener.onBtnCallClick(listContacts.get(getAdapterPosition()).getNumber());
                }
            });

            this.onBtnStarFavListener = onBtnStarFavListener;
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBtnStarFavListener.onBtnStarClick(listContacts.get(getAdapterPosition()).getNumber()
                            , listContacts.get(getAdapterPosition()).getName(),getAdapterPosition(),star);
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface OnBtnCallFavListener{

        void onBtnCallClick(String number);

    }

    public interface OnBtnStarFavListener{

        void onBtnStarClick(String number , String name ,int position ,  Button starBtn);

    }
}
