package pt.iservices.amarelinha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mariocosme on 25/05/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CATEGORIA = "food";

    public static final String COLUMN_ID_CATEGORIA = "id";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    private static final String DATABASE_NAME = "db1";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_TABLE_CATEGORIA = "create table "
            + TABLE_CATEGORIA + "(" + COLUMN_ID_CATEGORIA
            + " integer primary key autoincrement, " + COLUMN_IMAGE
            + " text not null, " + COLUMN_NAME
            + " text not null," + COLUMN_PRICE
            + " integer"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_TABLE_CATEGORIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
        onCreate(db);
    }

    public void resetDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
        onCreate(db);
    }

    public int getDBCount() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_CATEGORIA, null, null, null, null, null, null);
        int result = c.getCount();
        c.close();
        return result;
    }

    public boolean isDbFilled() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_CATEGORIA, null, null, null, null, null, null);
        int result = c.getCount();
        c.close();

        if (result > 0)  {
            return true;
        } else {
            return false;
        }
    }

    public void insertCategoria(Food f) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, f.getImage());
        values.put(COLUMN_NAME, f.getName());
        values.put(COLUMN_PRICE, f.getPrice());
        database.insert(TABLE_CATEGORIA, null, values);
        database.close();
    }

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> foods = new ArrayList<Food>();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food f = new Food();
                f.setImage(cursor.getString(1));
                f.setName(cursor.getString(2));
                f.setPrice(cursor.getInt(3));
                foods.add(f);
            } while (cursor.moveToNext());
        }
        db.close();
        return foods;
    }
}