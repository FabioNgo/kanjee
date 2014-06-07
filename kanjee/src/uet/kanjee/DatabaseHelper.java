/**
 * 
 */
package uet.kanjee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Fabio Ngo
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	static String DB_NAME = "kanjee.sqlt";

	public SQLiteDatabase myDatabase;
	Context myContext;

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @throws IOException
	 */

	public DatabaseHelper(Context context) throws IOException {

		super(context, context.getExternalFilesDir(
				context.ACCESSIBILITY_SERVICE).toString(), null, 1);

		this.myContext = context;
		myDatabase = SQLiteDatabase.openDatabase(
				context.getExternalFilesDir(context.ACCESSIBILITY_SERVICE)
						.toString() + "/" + DB_NAME, null,
				SQLiteDatabase.OPEN_READONLY);
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * 
	 * @return the array of all characters in database
	 */
	public List<KCharacter> getAllChars() {
		List<KCharacter> chars = new ArrayList<KCharacter>();
		String selectQuery = "SELECT  * FROM " + "CHARACTERS";
		Cursor c = myDatabase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			int a = 0;
			do {
				KCharacter character = new KCharacter(c.getInt(c
						.getColumnIndex("ID")), c.getInt(c
						.getColumnIndex("NUMSTROKES")), c.getString(c
						.getColumnIndex("TEXT")), c.getString(c
						.getColumnIndex("PINYIN")), c.getString(c
						.getColumnIndex("MEANING")), c.getString(c
						.getColumnIndex("OTHER")));
				// adding to tags list
				chars.add(character);
			} while (c.moveToNext());
		}
		return chars;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}