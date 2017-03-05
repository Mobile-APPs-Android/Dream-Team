//package com.example.dreamteam.beergram.contacts;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import com.example.dreamteam.beergram.BeergramApplication;
//import com.example.dreamteam.beergram.R;
//import com.example.dreamteam.beergram.camera.CameraFragment;
//import com.example.dreamteam.beergram.contacts.providers.ContactsObserver;
//import com.example.dreamteam.beergram.utils.BeergramProgressDialog;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
//public class ContactsActivity extends AppCompatActivity {
//    private ListView lvContacts;
//    private ArrayAdapter<String> adapter;
//
//    @Inject
//    ContactsPresenter mPresenter;
//
//    @Inject
//    BeergramProgressDialog mDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contacts);
//
//        ContactsFragment contactsFragment =
//                (ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_contacts);
//
//        if (contactsFragment == null) {
//            contactsFragment = ContactsFragment.newInstance();
//
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragment_contacts, contactsFragment).commit();
//
//            this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//            this.lvContacts = (ListView) this.findViewById(R.id.lvContacts);
//            this.lvContacts.setAdapter(this.adapter);
//        }
//
//        DaggerContactsComponent
//                .builder()
//                .repositoryComponent(((BeergramApplication) getApplication())
//                        .getRepositoryComponent())
//                .contactsModule(new ContactsModule(contactsFragment))
//                .build()
//                .inject(this);
//    }
//}
//
//
////    private void updateContacts(List<ContactsObserver.ContactInfo> contactInfos) {
////        final List<String> names = new ArrayList<>(contactInfos.size());
////        for (ContactsObserver.ContactInfo info : contactInfos) {
////            names.add(info.getName());
////        }
////
////        this.runOnUiThread(new Runnable() {
////            @Override
////            public void run() {
////                adapter.clear();
////                adapter.addAll(names);
////            }
////        });
////    }
////}
