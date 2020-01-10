package com.example.mycontacts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.R;
import com.example.mycontacts.models.ModelCalls;

import java.util.List;

public class CallsRvAdapter extends RecyclerView.Adapter<CallsRvAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;

    private List<ModelCalls> mlistCalls;
    private OnBtnCallListener monBtnCallListener;

    public CallsRvAdapter(Context context,List<ModelCalls> listCalls,OnBtnCallListener onBtnCallListener){
        mContext = context;
        mlistCalls = listCalls;
        monBtnCallListener = onBtnCallListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.item_calls,parent,false);

        ViewHolder viewHolder = new ViewHolder(view,monBtnCallListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TextView number,duration,date,time;

        number = holder.number;
        duration = holder.duration;
        date = holder.date;
        time = holder.time;


        if (mlistCalls.get(position).getName() == null){
            number.setText(mlistCalls.get(position).getNumber());
        }else {
            number.setText(mlistCalls.get(position).getName());
        }
        duration.setText("Duration: " + mlistCalls.get(position).getDuration());
        date.setText(mlistCalls.get(position).getDate());
        time.setText(mlistCalls.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return mlistCalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView number,duration,date,time;
        Button call;
        OnBtnCallListener onBtnCallListener;

        public ViewHolder(@NonNull View itemView,OnBtnCallListener onBtnCallListener) {
            super(itemView);

            number = itemView.findViewById(R.id.call_number);
            duration = itemView.findViewById(R.id.call_duration);
            date = itemView.findViewById(R.id.call_date);
            time = itemView.findViewById(R.id.time_call);
            call = itemView.findViewById(R.id.btn_call_call);

            this.onBtnCallListener =onBtnCallListener;

            call.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onBtnCallListener.onBtnCallClick(mlistCalls.get(getAdapterPosition()).getNumber());

        }
    }

    public interface OnBtnCallListener{

        void onBtnCallClick(String number);

    }

}
