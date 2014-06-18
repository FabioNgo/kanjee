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
	public ArrayList<KCharacter> getAllChars() {
		ArrayList<KCharacter> chars = new ArrayList<KCharacter>();
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
				//Log.d("character", chars.get(chars.size()-1).getText());
			} while (c.moveToNext());
		}
		return chars;
	}
	/**
	 * 
	 * @return the array of all characters in database
	 */
	public List<KRadical> getAllRadicals() {
		List<KRadical> radicals = new ArrayList<KRadical>();
		String selectQuery = "SELECT  * FROM " + "RADICALS ORDER BY `NUMSTROKES` ASC";
		Cursor c = myDatabase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			int a = 0;
			do {
				KRadical radical = new KRadical(
						c.getString(c.getColumnIndex("ID")),
						c.getInt(c.getColumnIndex("NUMSTROKES")),
						c.getString(c.getColumnIndex("TEXT")) 
						);
				// adding to tags list
				radicals.add(radical);
			} while (c.moveToNext());
		}
		return radicals;
	}
	/**
	 * 
	 * @param character
	 * @return all radicals combining to the character
	 */
	public ArrayList<KRadical> getRelatedRadicals(KCharacter character) {
		ArrayList<String> relatedRadicalsTexts = new ArrayList<String>();
		ArrayList<KRadical> radicals = new ArrayList<KRadical>();
		/**
		 * get all ID of related Radicals
		 */
		String selectQuery = "SELECT * FROM `RADICALS_CHARS_REL` WHERE `CHAR` LIKE '" 
		+ String.valueOf(character.getText())
		+ "' ORDER BY `ID`;";
		Cursor c = myDatabase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				String text = c.getString(c.getColumnIndex("RAD"));
				// adding to tags list
				relatedRadicalsTexts.add(text);
			} while (c.moveToNext());
		}
		/**
		 * get information of related Radicals
		 */
		
		for(int i=0; i<relatedRadicalsTexts.size();i++ ){
			
			String query = "SELECT * FROM `RADICALS` WHERE `TEXT` LIKE '" 
					+ String.valueOf(relatedRadicalsTexts.get(i))
					+ "';";
			Cursor cursor = myDatabase.rawQuery(query, null);
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					KRadical radical = new KRadical(
							cursor.getString(cursor.getColumnIndex("ID")),
							cursor.getInt(cursor.getColumnIndex("NUMSTROKES")),
							cursor.getString(cursor.getColumnIndex("TEXT")) 
							);
					 //adding to tags list
					radicals.add(radical);
					Log.d("Related Radical: "+character.getText(), radicals.get(radicals.size()-1).getText());
				} while (cursor.moveToNext());
			}
		}
		return radicals;
	}
	/**
	 * Get Related Radicals with the same numStrokes
	 * @param character
	 * @return
	 */
	public ArrayList<KRadical> getSimillarNumStrokeRadicals(KRadical radical) {
		ArrayList<KRadical> radicals = new ArrayList<KRadical>();
		
		String query = "SELECT * FROM `RADICALS` WHERE `NUMSTROKE` LIKE '"
				+ String.valueOf(radical.getNumStrokes()) + "';";
		Cursor cursor = myDatabase.rawQuery(query, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				KRadical tempRadical = new KRadical(cursor.getString(cursor
						.getColumnIndex("ID")), cursor.getInt(cursor
						.getColumnIndex("NUMSTROKES")), cursor.getString(cursor
						.getColumnIndex("TEXT")));
				 
				radicals.add(tempRadical);
			} while (cursor.moveToNext());
		}
		return radicals;
	}
	/**
	 * 
	 * @param radical
	 * @return all character contains the radical
	 */
	public ArrayList<KCharacter> getCharContainingRadical(KRadical radical) {
		ArrayList<KCharacter> characters = new ArrayList<KCharacter>();
		ArrayList<String> relatedChars = new ArrayList<String>();
		String query = "SELECT * FROM `RADICALS_CHARS_REL` WHERE `RAD` LIKE '"
				+ String.valueOf(radical.getText()) 
				+ "' ORDER BY `ID`;";
		Cursor c = myDatabase.rawQuery(query, null);
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				String text = c.getString(c.getColumnIndex("CHAR"));
				// adding to tags list
				relatedChars.add(text);
			} while (c.moveToNext());
		}
		/**
		 * get information of related Characters
		 */
		
		for(int i=0; i<relatedChars.size();i++ ){
			
			String query2 = "SELECT * FROM `CHARACTERS` WHERE `TEXT` LIKE '" 
					+ String.valueOf(relatedChars.get(i))
					+ "';";
			Cursor cursor = myDatabase.rawQuery(query2, null);
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					KCharacter character = new KCharacter(cursor.getInt(cursor
							.getColumnIndex("ID")), cursor.getInt(cursor
							.getColumnIndex("NUMSTROKES")), cursor.getString(cursor
							.getColumnIndex("TEXT")), cursor.getString(cursor
							.getColumnIndex("PINYIN")), cursor.getString(cursor
							.getColumnIndex("MEANING")), cursor.getString(cursor
							.getColumnIndex("OTHER")));
					characters.add(character);
				} while (cursor.moveToNext());
			}
		}
		return characters;
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
