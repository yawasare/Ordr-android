package productivity.yaw.asare.ordr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by yaw on 12/6/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Priorities.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    public static abstract class PriorityEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_DEADLINE = "deadline";
        public static final String COLUMN_NAME_IMPORTANCE = "importance";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_COMPLETED = "completed";
        public static final String COLUMN_NAME_COMPLETED_AT = "completed_at";
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXIST " + PriorityEntry.TABLE_NAME + " (" + PriorityEntry._ID +
            " INTEGER PRIMARY KEY," + PriorityEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
            PriorityEntry.COLUMN_NAME_CREATED_AT + TEXT_TYPE + COMMA_SEP +
            PriorityEntry.COLUMN_NAME_DEADLINE + TEXT_TYPE + COMMA_SEP +
            PriorityEntry.COLUMN_NAME_IMPORTANCE + INTEGER_TYPE + COMMA_SEP +
            PriorityEntry.COLUMN_NAME_DURATION + TEXT_TYPE + COMMA_SEP +
            PriorityEntry.COLUMN_NAME_COMPLETED + INTEGER_TYPE + COMMA_SEP +
            PriorityEntry.COLUMN_NAME_COMPLETED_AT + TEXT_TYPE + COMMA_SEP;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PriorityEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPriority(){


    }

    public void updatePriority(){


    }
}
