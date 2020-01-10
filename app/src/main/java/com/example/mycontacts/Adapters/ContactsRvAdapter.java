package com.example.mycontacts.Adapters;

import android.content.Context;
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

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.ViewHolder> {

    private Context mcontext;
    private LayoutInflater inflater;
    private List<ModelContacts> mlistContacts;
    private OnBtnCallContactListener monBtnCallContactListener;


    public ContactsRvAdapter(Context context,List<ModelContacts> listContacts,OnBtnCallContactListener onBtnCallContactListener){
        mlistContacts = listContacts;
        mcontext = context;
        monBtnCallContactListener = onBtnCallContactListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mcontext);

        View view = inflater.inflate(R.layout.items_contacts,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,monBtnCallContactListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView contact_name,contact_number;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;

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
        OnBtnCallContactListener onBtnCallContactListener;

        public ViewHolder(@NonNull View itemView,OnBtnCallContactListener onBtnCallContactListener) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            call = itemView.findViewById(R.id.btn_call_contacts);

            this.onBtnCallContactListener = onBtnCallContactListener;
            call.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onBtnCallContactListener.onBtnCallClick(mlistContacts.get(getAdapterPosition()).getNumber());
        }
    }

    public interface OnBtnCallContactListener{

        void onBtnCallClick(String number);

    }
}
