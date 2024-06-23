package fr.propan.solveit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creationSql = getCreationSql();
        Log.d(TAG, "Executing onCreate SQL: " + creationSql);
        db.execSQL(creationSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getDeleteSql());
        onCreate(db);
    }

    protected abstract String getCreationSql();

    protected abstract String getDeleteSql();
}
