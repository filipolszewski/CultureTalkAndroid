package olszewski.filip.pl.ctalk.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import olszewski.filip.pl.ctalk.MessageData;
import olszewski.filip.pl.ctalk.UserData;
import olszewski.filip.pl.ctalk.fragment.listItems.ContactListViewItem;
import olszewski.filip.pl.ctalk.utility.SharedPreferencesUtil;

/**
 * Created by Filip on 2016-06-23.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "messageManager";
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    private static final String CREATE_TABLE_MESSAGE = "CREATE TABLE message(id " +
            "INTEGER PRIMARY KEY autoincrement, messageString TEXT, date TEXT, contactID TEXT, " +
            "contactName TEXT, sentOrReceived integer)";
    private final Context context;

    public void saveReceivedMessages(List<MessageData> newMessages) {
        if (newMessages.size() != 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            Integer maxID = 0;
            for (MessageData message : newMessages) {
                if (message.getId() > maxID) {
                    maxID = message.getId();
                }
                saveSingleMessage(db, 1, message);
            }
            db = this.getReadableDatabase();
            if (db != null && db.isOpen()) {
                db.close();
            }
            SharedPreferencesUtil.saveLastMessageID(context, maxID);
        }
    }

    private void saveSingleMessage(SQLiteDatabase db, Integer sentOrReceived, MessageData message) {
        boolean closeDbAfterSave = false;
        if (db == null) {
            db = this.getWritableDatabase();
            closeDbAfterSave = true;
        }
        ContentValues values = new ContentValues();
        values.put("messageString", message.getMessage());
        values.put("date", message.getDateSent());
        values.put("contactID", message.getToUser().getId());
        values.put("contactName", message.getToUser().getName());
        values.put("sentOrReceived", sentOrReceived);
        Long id = db.insert("message", null, values);
        if (closeDbAfterSave) {
            db.close();
        }
    }


    // metoda potrzebna do implementacji "frameworka" https://github.com/sanathp/DatabaseManager_For_Android
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"mesage"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);
        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});
            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {
                alc.set(0, c);
                c.moveToFirst();
                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public Message getLastMessageWithContactID(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] whereArgs = new String[]{"" + id};
        Cursor c = db.query("message", null, "contactID=?", whereArgs, null, null, "id");
        c.moveToFirst();
        Message msg;
        if (c.getCount() > 0) {
            msg = new Message(c.getInt(c.getColumnIndex("id")), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getInt(5) == 1);
        } else {
            c.close();
            db.close();
            return null;
        }
        c.close();
        db.close();
        return msg;
    }

    public void saveMessage(Integer idUser, String string) {
        List<ContactListViewItem> contacts = SharedPreferencesUtil.getAddedContacts(context);
        MessageData message;
        for (ContactListViewItem item : contacts) {
            if (item.getId().equals(idUser)) {
                message = new MessageData(idUser, new UserData(item.getId(), item.getName(), item.getEmail()), string, df.format(new Date()));
                saveSingleMessage(null, 0, message);
                break;
            }
        }
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL(CREATE_TABLE_MESSAGE);
        }
        if (oldVersion == 2 && newVersion == 3) {
            db.execSQL("DROP TABLE message");
        }
        if (oldVersion == 3 && newVersion == 4) {
            db.execSQL(CREATE_TABLE_MESSAGE);
        }
    }

    public List<Message> getConversationWith(Integer idUser) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] whereArgs = new String[]{"" + idUser};
        Cursor c = db.query("message", null, "contactID=?", whereArgs, null, null, "id");
        c.moveToLast();
        c.moveToNext();
        List<Message> list = new ArrayList<>();
        if (c.getCount() > 0) {
            while (c.moveToPrevious()) {
                Message msg = new Message(c.getInt(c.getColumnIndex("id")), c.getString(1),
                        c.getString(2), c.getString(3), c.getString(4), c.getInt(5) == 1);
                list.add(msg);
            }
            c.close();
            db.close();
            return list;
        } else {
            c.close();
            db.close();
            return null;
        }
    }
}
