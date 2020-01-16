package com.example.mycontacts.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.Adapters.ContactsRvAdapter;
import com.example.mycontacts.R;
import com.example.mycontacts.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

public class FragmentContacts extends Fragment implements ContactsRvAdapter.OnBtnCallContactListener, ContactsRvAdapter.OnBtnStarContactListener {

    private RecyclerView recyclerView;
    private Button btnAddContact;

    public FragmentContacts() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        setContactsAdapter();
        clickOnAddContactBtn();

    }

    private void clickOnAddContactBtn(){

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentAddContact fragmentAddContact = new FragmentAddContact();
                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_contact,fragmentAddContact)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void setContactsAdapter(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        ContactsRvAdapter adapter = new ContactsRvAdapter(getContext(),getContacts(),this,this);
        recyclerView.setAdapter(adapter);
    }


    private List<ModelContacts> getContacts(){

        List<ModelContacts> list = new ArrayList<>();

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        cursor.moveToFirst();
        while (cursor.moveToNext()){

            list.add(new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )),cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));

            Log.d("TAG", "contacts ok " + cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
        }
        return list;
    }

    @Override
    public void onBtnCallClick(String number) {

        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},123);

        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        getActivity().startActivity(intent);

    }



    @Override
    public void onBtnStarClick(String number, String name ) {

        Log.d("TAG","Name : " + name + " Number : " + number);

//        FavList favList = MyPreferenceManager.getInstance(getActivity()).getFavList();
//        ModelContacts newfav = new ModelContacts(name,number);
//        newfav.setNumber(number);
//        newfav.setName(name);
//        favList.addContactToList(newfav);
//        MyPreferenceManager.getInstance(getActivity()).putFavList(favList);



    }


    private void findViews(View view){
        recyclerView = view.findViewById(R.id.rv_contacts);
        btnAddContact = view.findViewById(R.id.btn_add_contact);
    }
}
