import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class KanjeeImport {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new InputStreamReader(
					new FileInputStream("radicals.txt"), "UTF-8"));
		} catch (Exception e) {
			return;
		}

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("radicals.sql"), "UTF-8"));

		int id = 1;
		Vector<Integer> chars = new Vector<Integer>();
		while (true) {
			String line = bufReader.readLine();
			if (line == null)
				break;
			String key = line.substring(0, 2);
			line = line.substring(2);
			// System.out.println(key + " " + line);
			for (int i = 0; i < line.length(); i++) {
				out.write("INSERT INTO `RADICALS` VALUES(" + id + ",'" + key
						+ "','" + line.charAt(i) + "');\n");
				// System.out.println("INSERT INTO `RADICALS` VALUES("+id+",'" +
				// key + "','"
				// + line.charAt(i) + "');");
				chars.add((int) line.charAt(i));
				id++;
			}
		}
		out.close();

		try {
			bufReader = new BufferedReader(new InputStreamReader(
					new FileInputStream("rad_char.txt"), "UTF-8"));
		} catch (Exception e) {
			return;
		}
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				"rad_char.sql"), "UTF-8"));

		id = 1;
		int iRadicals = 0;
		while (true) {
			String line = bufReader.readLine();
			if (line == null)
				break;
			for (int i = 0; i < line.length(); i++) {
				char t = line.charAt(i);
				if ((t <= '9' && t >= '1') || t == '0')
					continue;
				out.write("INSERT INTO `RADICALS_CHARS_REL` VALUES(" + id + ",'"
						+ (char) chars.elementAt(iRadicals).intValue() + "','"
						+ line.charAt(i) + "');\n");
				id++;
			}
			iRadicals++;
		}
		
		out.close();

	}
}
