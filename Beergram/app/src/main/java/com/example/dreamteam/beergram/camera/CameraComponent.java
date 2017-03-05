package com.example.dreamteam.beergram.camera;

import com.example.dreamteam.beergram.data.RepositoryComponent;

        import com.example.dreamteam.beergram.utils.FragmentScoped;

        import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = CameraModule.class)
public interface CameraComponent {
    void inject(CameraActivity activity);
}