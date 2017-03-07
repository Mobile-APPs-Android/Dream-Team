package com.example.dreamteam.beergram.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;

import javax.inject.Inject;

public class ContactsActivity extends AppCompatActivity {

    @Inject
    ContactsPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ContactsFragment contactsFragment =
                (ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (contactsFragment == null) {
            contactsFragment = ContactsFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, contactsFragment)
                    .commit();
        }

        DaggerContactsComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .contactsModule(new ContactsModule(contactsFragment))
                .build()
                .inject(this);
    }
}
