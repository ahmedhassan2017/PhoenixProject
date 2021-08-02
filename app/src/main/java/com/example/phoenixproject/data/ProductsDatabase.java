
package com.example.phoenixproject.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.phoenixproject.Models.ProductsDatabaseModel;


//
//// TODO 1.3 ba3ml el database nafsaha (singleton Pattern)
@Database(entities = ProductsDatabaseModel.class,version = 10)      // version da 34an law 3amlt ta3del el rakam byt8ayer
public abstract class ProductsDatabase extends RoomDatabase {

    private static ProductsDatabase INSTANCE;

    public abstract ProductsDao productsDao();  // b3rf el Doa bta3y

    public static synchronized ProductsDatabase getINSTANCE(Context context)  // 34an lama agy a5od object yb2a wa7ed bas
    {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductsDatabase.class, "products_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
