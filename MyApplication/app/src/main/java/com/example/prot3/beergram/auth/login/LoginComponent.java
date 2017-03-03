package com.example.prot3.beergram.auth.login;

import com.example.prot3.beergram.auth.login.LoginActivity;
import com.example.prot3.beergram.auth.login.LoginModule;
import com.example.prot3.beergram.data.RepositoryComponent;

import dagger.Component;

@Component(dependencies = RepositoryComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}