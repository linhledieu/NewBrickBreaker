package main.Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map;
import java.util.Map.Entry;

public class Scoreboard {
	public void writeScore(String name, int score) throws IOException {
		try {
			FileWriter myWriter = new FileWriter("Resources/scores.txt", true);
			BufferedWriter bw = new BufferedWriter(myWriter);
			BufferedReader fileRead = new BufferedReader(new FileReader("Resources/scores.txt"));
			bw.newLine();
			bw.write(name + ", " + score);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LinkedHashMap<String, Integer> readScore() throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader("Resources/scores.txt"));
		ArrayList<Integer> scores = new ArrayList<>();
		String temp = fileReader.readLine();
		Map<String, Integer> map = new HashMap<String, Integer>();

		while (temp != null) {
			String[] line = temp.split(", ");
			String name = line[0];
			int score = Integer.parseInt(line[1]);
			temp = fileReader.readLine();
			map.put(name, score);
		}
		Set<Entry<String, Integer>> entries = map.entrySet();
		ArrayList<Entry<String, Integer>> entryList = new ArrayList<>(entries);
		entryList.sort(valueComparator);;
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		for (Entry<String, Integer> entry : entryList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	Comparator<Entry<String, Integer >> valueComparator = new Comparator<Entry<String, Integer>>() {

		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			Integer v1 = o1.getValue();
			Integer v2 = o2.getValue();
			return v2.compareTo(v1);
		}

	};

}



//package main;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class Scoreboard {
//	public void writeScore(int score) throws IOException {
//		if (checkScore(score) == true) {
//			try {
//				FileWriter myWriter = new FileWriter("scores.txt", true);
//			    BufferedWriter bw = new BufferedWriter(myWriter);
//			    bw.write(String.valueOf(score));
//			    bw.newLine();
//			    bw.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public boolean checkScore(int x) throws IOException {
//		ArrayList<Integer> temp = readScore();
//		boolean check = false;
//		for (int i: temp) {
//			if (x > i) {
//				check = true;
//			}
//		}
//		return check;
//	}
//
//	public ArrayList<Integer> readScore() throws IOException {
//		BufferedReader fileReader = new BufferedReader(new FileReader("scores.txt"));
//	    ArrayList<Integer> scores = new ArrayList<>();
//	    String line = fileReader.readLine();
//	    while (line != null) {
//	    	scores.add(Integer.parseInt(line));
//	    	line = fileReader.readLine();
//	    }
//	    fileReader.close();
//	    scores.sort(Collections.reverseOrder());
//	    return scores;
//	}
//}


