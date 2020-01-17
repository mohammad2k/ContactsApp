package com.example.mycontacts.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.Adapters.ContactsRvAdapter;
import com.example.mycontacts.Adapters.FavRvAdapter;
import com.example.mycontacts.MyPreferenceManager;
import com.example.mycontacts.R;

public class FragmentFav extends Fragment implements FavRvAdapter.OnBtnCallFavListener {

    private RecyclerView recyclerView;
    FavRvAdapter adapter;

    public FragmentFav() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        setFavAdapter();
    }


    private void setFavAdapter(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FavRvAdapter(getContext(), MyPreferenceManager.getInstance(getActivity()).getContactList(),this);
        recyclerView.setAdapter(adapter);

    }

    private void findViews(View view){
        recyclerView = view.findViewById(R.id.rv_fav);
    }

    @Override
    public void onBtnCallClick(String number) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},123);

        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        getActivity().startActivity(intent);

    }
}
