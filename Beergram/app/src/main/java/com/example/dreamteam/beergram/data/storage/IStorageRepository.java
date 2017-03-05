package com.example.dreamteam.beergram.data.storage;


import java.io.File;
import io.reactivex.Observable;

public interface IStorageRepository {
    Observable<Boolean> saveImage(File image);
}
