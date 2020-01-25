package com.example.mycontacts.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ModuleInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.Adapters.ContactsRvAdapter;
import com.example.mycontacts.Adapters.FavRvAdapter;
import com.example.mycontacts.MyPreferenceManager;
import com.example.mycontacts.R;
import com.example.mycontacts.models.ModelContacts;
import com.example.mycontacts.models.ModelFav;

import java.util.List;

public class FragmentFav extends Fragment implements FavRvAdapter.OnBtnCallFavListener , FavRvAdapter.OnBtnStarFavListener {

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

        ModelFav favListObject = MyPreferenceManager.getInstance(getActivity()).getFavList();

        adapter = new FavRvAdapter(getContext(), favListObject.getFavList() ,this,this);
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

    @Override
    public void onBtnStarClick(String number, String name,int position, Button starBtn) {
        List<ModelContacts> list = MyPreferenceManager.getInstance(getActivity()).getContactList();
        ModelFav favListObject = MyPreferenceManager.getInstance(getActivity()).getFavList();

        favListObject.getFavList().remove(position);

        for (int i =0 ; i< list.size() ; i++){

            if (list.get(i).getName().equals(name)){

                list.get(i).setStar(false);
                MyPreferenceManager.getInstance(getActivity()).putContactList(list);

            }

        }

        MyPreferenceManager.getInstance(getActivity()).putFavList(favListObject);
        setFavAdapter();

    }


    //برای ابدیت کردن فرگمنت
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.detach(FragmentFav.this).attach(FragmentFav.this).commit();
            setFavAdapter();
        }
    }
}
