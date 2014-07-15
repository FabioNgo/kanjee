package uet.kanjee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
/**
 * @author Huy
 *
 */
public class MainActivity extends Activity {
	public static DatabaseHelper db;
	ImageView im1;
	static final String DB_NAME = "kanjee.sqlt";
	public static int screenHeight;
	public static int screenWidth;
	public static Typeface font;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		TextView appName = (TextView)findViewById(R.id.app_name);
		TextView function1 = (TextView)findViewById(R.id.function1);
		function1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Menu1FragmentActivity.class);
				startActivity(i); 
			}
		});
		DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        appName.setTextSize((float) (screenHeight/50.0));
        font = Typeface.createFromAsset(getAssets(), "font.OTF");
	
		copyAssets();
		try {
			db = new DatabaseHelper(getApplicationContext());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
		}
		
		//ArrayList<KCharacter> chars = (ArrayList<KCharacter>) db.getAllChars();
		
	}
	private void copyAssets() {
	    AssetManager assetManager = getAssets();
	    String[] files = null;
	    try {
	        files = assetManager.list("");
	    } catch (IOException e) {
	        //Log.e("tag", "Failed to get asset file list.", e);
	    }
	    for(String filename : files) {
	        InputStream in = null;
	        OutputStream out = null;
	        try {
	          in = assetManager.open(filename);
	          File outFile = new File(getExternalFilesDir(getApplicationContext().ACCESSIBILITY_SERVICE).toString()
	        		  , filename);
	          out = new FileOutputStream(outFile);
	          copyFile(in, out);
	          in.close();
	          in = null;
	          out.flush();
	          out.close();
	          out = null;
	        } catch(IOException e) {
	            //Log.e("tag", "Failed to copy asset file: " + filename, e);
	        }       
	    }
	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
