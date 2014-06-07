package uet.kanjee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
/**
 * Thay doi code o day
 * @author Huy
 *
 */
public class MainActivity extends ActionBarActivity {
	DatabaseHelper db;
	static final String DB_NAME = "kanjee.sqlt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		try {
			db = new DatabaseHelper(getApplicationContext());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
		}
		
		ArrayList<KCharacter> chars = (ArrayList<KCharacter>) db.getAllChars();
		
		TextView tv = (TextView)findViewById(R.id.textView1);
		
		tv.setText(String.valueOf(chars.size()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * copy database file from assets to storage
	 * @throws IOException
	 */
	private void copyDatabase() throws IOException {
		// TODO Auto-generated method stub
		InputStream myinput = getAssets().open(DB_NAME);

		// Path to the just created empty db

		// Open the empty db as the output stream
		File outputFile = new File(
				getExternalFilesDir(ACCESSIBILITY_SERVICE),DB_NAME);
		OutputStream output = new FileOutputStream(outputFile);

		// transfer byte to inputfile to outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myinput.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}

		// Close the streams
		output.flush();
		output.close();
		myinput.close();

	}
	
	

}
