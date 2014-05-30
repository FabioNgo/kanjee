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
		
		super(context, context.getExternalFilesDir(context.ACCESSIBILITY_SERVICE).toString(), null, 1);
		
		this.myContext = context;
		copyDatabase();
		myDatabase = SQLiteDatabase.openDatabase(context.getExternalFilesDir(context.ACCESSIBILITY_SERVICE).toString() + "/" + DB_NAME,
												null, SQLiteDatabase.OPEN_READONLY);
		// TODO Auto-generated constructor stub
	}	
	private void copyDatabase() throws IOException {
		// TODO Auto-generated method stub
		InputStream myinput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db

        //Open the empty db as the output stream
		File outputFile = new File(myContext.getExternalFilesDir(myContext.ACCESSIBILITY_SERVICE),DB_NAME);
		
        OutputStream output = new FileOutputStream(outputFile);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            output.write(buffer,0,length);
        }

        //Close the streams
        output.flush();
        output.close();
        myinput.close();
		
	}



	

	public List<KCharacter> getAllChars() {
	    List<KCharacter> chars = new ArrayList<KCharacter>();
	    String selectQuery = "SELECT  * FROM " + "CHARACTERS";
	 
	    
	 
	    
	    Cursor c = myDatabase.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (c.moveToFirst()) {
	    	int a = 0;
	        do {
	        	
	            KCharacter character = new KCharacter(c.getInt(c.getColumnIndex("ID")),
	            							c.getInt(c.getColumnIndex("NUMSTROKES")),
	            							c.getString(c.getColumnIndex("TEXT")),
	            							c.getString(c.getColumnIndex("PINYIN")),
	            							c.getString(c.getColumnIndex("MEANING")),
	            							c.getString(c.getColumnIndex("OTHER"))
	            		);
	            
	 
	            // adding to tags list
	            chars.add(character);
	            a++;
	            Toast.makeText(myContext, String.valueOf(a), Toast.LENGTH_SHORT).show();
	            
	        } while (c.moveToNext());
	    }
	    return chars;
	}

	private boolean checkDatabase() {
		// TODO Auto-generated method stub   
            try {
				InputStream dbfile = myContext.getAssets().open(DB_NAME);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return false;
			}
            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
        
        return true;
		
	}



	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
