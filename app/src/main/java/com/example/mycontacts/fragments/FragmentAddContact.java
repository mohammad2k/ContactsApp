package com.example.mycontacts.fragments;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycontacts.R;

import java.util.ArrayList;

public class FragmentAddContact extends Fragment {
    private EditText name;
    private EditText number;
    private Button create;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_contact,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

        createContact();

    }

    private void createContact(){

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                intent.putExtra(ContactsContract.Intents.Insert.PHONE, number.getText().toString())
                        .putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());

                startActivityForResult(intent,1);

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1)
        {

            Toast.makeText(getContext(), "Added Contact", Toast.LENGTH_SHORT).show();

        }
    }

    private void findViews(View view){
        name = view.findViewById(R.id.edit_text_name);
        number = view.findViewById(R.id.edit_text_number);
        create = view.findViewById(R.id.btn_create_contact);

    }
}
