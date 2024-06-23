package fr.propan.solveit.database;

import android.content.ContentValues;
import android.database.Cursor;

import fr.propan.solveit.entities.GameHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GameHistoryDao extends BaseDao<GameHistory> {

    public static final String tableName = "GameHistory";
    public static final String username = "username";
    public static final String score = "score";
    public static final String date = "date";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public GameHistoryDao(DatabaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected void putValues(ContentValues values, GameHistory entity) {
        values.put(username, entity.getUsername());
        values.put(score, entity.getScore());
        values.put(date, dateFormat.format(entity.getDate()));
    }

    @Override
    protected GameHistory getEntity(Cursor cursor) {
        GameHistory toReturn = new GameHistory();

        int indexUsername = cursor.getColumnIndex(username);
        toReturn.setUsername(cursor.getString(indexUsername));

        int indexScore = cursor.getColumnIndex(score);
        toReturn.setScore(cursor.getInt(indexScore));

        int indexDate = cursor.getColumnIndex(date);
        try {
            toReturn.setDate(dateFormat.parse(cursor.getString(indexDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}
