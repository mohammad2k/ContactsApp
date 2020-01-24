package com.example.mycontacts.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TimeFormatException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.Adapters.CallsRvAdapter;
import com.example.mycontacts.MainActivity;
import com.example.mycontacts.R;
import com.example.mycontacts.models.ModelCalls;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class FragmentCalls extends Fragment implements CallsRvAdapter.OnBtnCallListener {

    private RecyclerView recyclerView;
    private final int MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 123;

    public FragmentCalls() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calls, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_calls);
        setCallAdapter();

    }

    private void setCallAdapter(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        CallsRvAdapter adapter = new CallsRvAdapter(getContext(), getCallLogs(),this);
        recyclerView.setAdapter(adapter);

    }


    private List<ModelCalls> getCallLogs() {

        List<ModelCalls> list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CALL_LOG},MY_PERMISSIONS_REQUEST_READ_CALL_LOG);

        }

        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, CallLog.Calls.DATE + " DESC");


        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date_idx = cursor.getColumnIndex(CallLog.Calls.DATE);


        cursor.moveToFirst();
        while (cursor.moveToNext()){

            Date date = new Date(Long.valueOf(cursor.getString(date_idx)));

            int durationTime = (cursor.getInt(duration));

            String mnth_day,week_day,month,year,time;

            mnth_day = (String) DateFormat.format("dd",date);
            week_day = (String) DateFormat.format("EEEE",date);
            month = (String) DateFormat.format("MMMM",date);
            year = (String) DateFormat.format("yyyy",date);
            time = (String) DateFormat.format("hh:mm",date);

            int minutes = durationTime / 60;
            int seconds = durationTime % 60;


            list.add(new ModelCalls(cursor.getString(number),String.format("%02d:%02d",minutes,seconds),
                    week_day + " " + mnth_day + " " + month + " " + year ,time,cursor.getString(name)));


            Log.d("TAG","ok shod " + cursor.getString(number));

        }

        return list;
    }

    @Override
    public void onBtnCallClick(String number) {

        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_READ_CALL_LOG);

        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        getActivity().startActivity(intent);

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(FragmentCalls.this).attach(FragmentCalls.this).commit();
        }
    }
}
