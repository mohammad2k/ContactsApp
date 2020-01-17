package com.example.mycontacts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.MyPreferenceManager;
import com.example.mycontacts.R;
import com.example.mycontacts.models.ModelContacts;

import java.util.List;

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.ViewHolder> {

    private Context mcontext;
    private LayoutInflater inflater;
    private List<ModelContacts> mlistContacts;
    private OnBtnCallContactListener monBtnCallContactListener;
    private OnBtnStarContactListener monBtnStarContactListener;


    public ContactsRvAdapter(Context context,List<ModelContacts> listContacts,OnBtnCallContactListener onBtnCallContactListener, OnBtnStarContactListener onBtnStarContactListener){
        mlistContacts = listContacts;
        mcontext = context;
        monBtnCallContactListener = onBtnCallContactListener;
        monBtnStarContactListener = onBtnStarContactListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mcontext);

        View view = inflater.inflate(R.layout.items_contacts,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,monBtnCallContactListener,monBtnStarContactListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView contact_name,contact_number;
        Button starBtn;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
        starBtn = holder.star;

        if (mlistContacts.get(position).getStar()== true){
            starBtn.setBackgroundResource(R.drawable.ic_star_24dp);
        }else if (mlistContacts.get(position).getStar()== false){
            starBtn.setBackgroundResource(R.drawable.ic_star_border_24dp);
        }

        contact_name.setText(mlistContacts.get(position).getName());
        contact_number.setText(mlistContacts.get(position).getNumber());

    }


    @Override
    public int getItemCount() {
        return mlistContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView contact_name,contact_number;
        Button call;
        Button star;
        OnBtnCallContactListener onBtnCallContactListener;
        OnBtnStarContactListener onBtnStarContactListener;

        public ViewHolder(@NonNull View itemView,OnBtnCallContactListener nonBtnCallContactListener, OnBtnStarContactListener nonBtnStarContactListener) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            call = itemView.findViewById(R.id.btn_call_contacts);
            star = itemView.findViewById(R.id.btn_star_contacts);


            this.onBtnCallContactListener = nonBtnCallContactListener;
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBtnCallContactListener.onBtnCallClick(mlistContacts.get(getAdapterPosition()).getNumber());
                }
            });

            this.onBtnStarContactListener = nonBtnStarContactListener;
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBtnStarContactListener.onBtnStarClick(mlistContacts.get(getAdapterPosition()).getNumber()
                            , mlistContacts.get(getAdapterPosition()).getName(),getAdapterPosition(),star);
                }
            });


        }


        @Override
        public void onClick(View v) {

//            onBtnCallContactListener.onBtnCallClick(mlistContacts.get(getAdapterPosition()).getNumber());
//
//            onBtnStarContactListener.onBtnStarClick(mlistContacts.get(getAdapterPosition()).getNumber()
//                    , mlistContacts.get(getAdapterPosition()).getName());
        }
    }

    public interface OnBtnCallContactListener{

        void onBtnCallClick(String number);

    }

    public interface OnBtnStarContactListener{

        void onBtnStarClick(String number , String name , int position , Button starBtn);

    }
}
