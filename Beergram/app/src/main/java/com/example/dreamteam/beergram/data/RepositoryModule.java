package com.example.dreamteam.beergram.data;

import com.example.dreamteam.beergram.data.authprovider.FirebaseAuthProvider;
import com.example.dreamteam.beergram.data.authprovider.IAuthProvider;
import com.example.dreamteam.beergram.data.local.ILocalRepository;
import com.example.dreamteam.beergram.data.local.LocalDb.ILocalDbRepository;
import com.example.dreamteam.beergram.data.local.LocalDb.SqlLiteDb;
import com.example.dreamteam.beergram.data.local.LocalRepository;
import com.example.dreamteam.beergram.data.remote.FirebaseRemoteRepository;
import com.example.dreamteam.beergram.data.remote.IRemoteRepository;
import com.example.dreamteam.beergram.data.storage.IStorageRepository;
import com.example.dreamteam.beergram.data.storage.StorageRepository;
import com.example.dreamteam.beergram.utils.IRandomStringProvider;
import com.example.dreamteam.beergram.utils.RandomStringProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract IAuthProvider provideAuthProvider(FirebaseAuthProvider provider);

    @Binds
    abstract IRepository provideRepository(Repository repository);

    @Binds
    abstract IRemoteRepository provideRemoteRepository(FirebaseRemoteRepository remoteRepository);

    @Binds
    abstract ILocalRepository provideLocalRepository(LocalRepository localRepository);

    @Binds
    abstract ILocalDbRepository provideLocalDbRepository(SqlLiteDb localDbRepository);

    @Binds
    abstract IRandomStringProvider provideRandomStringProvider(RandomStringProvider randomStringProvider);

    @Binds
    abstract IStorageRepository provideStorageRepository(StorageRepository storageRepository);

    @Binds
    abstract ILocationProvider provideLocationProcider(LocationProvider locationProvider);
}
