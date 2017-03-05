package com.example.dreamteam.beergram.data.storage;

import com.google.firebase.storage.StorageReference;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class StorageRepository implements IStorageRepository {
    private StorageReference storageRef;

    @Inject
    public StorageRepository(StorageReference storageRef) {
        this.storageRef = storageRef;
    }

    @Override
    public Observable<Boolean> saveImage(File image) {
        return Observable.create(e -> {
            this.storageRef.getFile(image)
                    .addOnSuccessListener((taskSnapshot) -> {
                        e.onNext(true);
                    })
                    .addOnFailureListener((task) -> {
                       e.onNext(false);
                    });
            });
    }
}
