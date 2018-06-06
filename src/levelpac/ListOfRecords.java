package levelpac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListOfRecords {
	private ArrayList<RecordField> list;
	private final Comparator<RecordField> BY_POINTS = new ByPoints();
	//d
	public ListOfRecords(String str) throws IOException {
		list = new ArrayList<RecordField>();
		BufferedReader first = new BufferedReader(new FileReader("results.txt"));
		String st;
		while ((st = first.readLine()) != null) {
			String[] str1 = st.split("~");
			RecordField rec = new RecordField(str1[0], str1[1], str1[2]);
			list.add(rec);
		}
		first.close();
		Collections.sort(list, BY_POINTS);
		Collections.reverse(list);
	}
	//d
	public void add(RecordField cur) throws IOException {
		list.add(cur);
		Collections.sort(list, BY_POINTS);
		Collections.reverse(list);
		try {
			FileWriter fstream1 = new FileWriter("results.txt");
			BufferedWriter out1 = new BufferedWriter(fstream1);
			out1.write("");
			out1.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("results.txt", true)));
		for (int i = 0; i < list.size(); i++) {
			out1.println(list.get(i).getName() + "~" + list.get(i).getLevel() + "~" + list.get(i).getPoints());
		}
		out1.close();
	}
	//d
	public int getSize() {
		return list.size();
	}
	//d
	public RecordField getObject(int i) {
		return list.get(i);
	}
	//d
	public int getIndex(RecordField rf) {
		return list.indexOf(rf);
	}

}
//d
class ByPoints implements Comparator<RecordField> {

	public int compare(RecordField first, RecordField second) {
		return Integer.valueOf(first.getPoints()) - Integer.valueOf(second.getPoints());
	}
}
