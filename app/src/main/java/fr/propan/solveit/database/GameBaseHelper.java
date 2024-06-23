package fr.propan.solveit.database;

import android.content.Context;

public class GameBaseHelper extends DatabaseHelper {
    public GameBaseHelper(Context context, String dataBaseName, int dataBaseVersion) {
        super(context, dataBaseName, dataBaseVersion);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + GameHistoryDao.tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GameHistoryDao.username + " VARCHAR(50) NOT NULL, " +
                GameHistoryDao.score + " INTEGER NOT NULL, " +
                GameHistoryDao.date + " DATETIME NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS " + GameHistoryDao.tableName;
    }
}
