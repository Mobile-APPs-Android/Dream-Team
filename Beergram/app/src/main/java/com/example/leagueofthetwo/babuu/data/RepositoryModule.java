package com.example.leagueofthetwo.babuu.data;

import com.example.leagueofthetwo.babuu.data.authprovider.FirebaseAuthProvider;
import com.example.leagueofthetwo.babuu.data.authprovider.IAuthProvider;
import com.example.leagueofthetwo.babuu.data.local.ILocalRepository;
import com.example.leagueofthetwo.babuu.data.local.LocalDb.ILocalDbRepository;
import com.example.leagueofthetwo.babuu.data.local.LocalDb.SqlLiteDb;
import com.example.leagueofthetwo.babuu.data.local.LocalRepository;
import com.example.leagueofthetwo.babuu.data.remote.FirebaseRemoteRepository;
import com.example.leagueofthetwo.babuu.data.remote.IRemoteRepository;
import com.example.leagueofthetwo.babuu.utils.IRandomStringProvider;
import com.example.leagueofthetwo.babuu.utils.RandomStringProvider;

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
}
