package com.example.dreamteam.beergram.contacts;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactsModule {

    private final ContactsContract.View view;

    public ContactsModule(ContactsContract.View view){
        this.view = view;
    }

    @Provides
    ContactsContract.View provideContactsView(){
        return this.view;
    }
}
