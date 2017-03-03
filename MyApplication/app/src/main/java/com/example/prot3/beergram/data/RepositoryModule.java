package com.example.prot3.beergram.data;

import com.example.prot3.beergram.data.authprovider.IFirebaseAuthProvider;
import com.example.prot3.beergram.utils.IRandomStringProvider;
import com.example.prot3.beergram.utils.RandomStringProvider;
import com.google.firebase.auth.FirebaseAuthProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract IFirebaseAuthProvider provideAuthProvider(FirebaseAuthProvider provider);

    @Binds
    abstract IRepository provideRepository(Repository repository);

    @Binds
    abstract IRandomStringProvider provideRandomStringProvider(RandomStringProvider randomStringProvider);
}
