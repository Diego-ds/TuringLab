package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.TuringMachine;

public class Main {
	TuringMachine objTur;
	
	public Main() {
		objTur=new TuringMachine();
	}
	
	public static void main(String[] args) throws IOException {
		Main obj = new Main();
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/result.txt"));
		BufferedReader br = new BufferedReader(new FileReader("data/info_lines.txt"));
		while(br.readLine()!=null) {
			String line = br.readLine();
			for(int i=0;i<line.length();i++) {
				int head=Character.getNumericValue(line.charAt(i));
				i++;
				int action = Character.getNumericValue(line.charAt(i));
				switch(action) {
				case 0:
					bw.write(obj.readTape(head)+"\n");
					break;
				case 1:
					i++;
					char letter = line.charAt(i);
					obj.addTape(head, letter);
					break;
				case 2:
					obj.deleteTape(head);
					break;
				}
				obj.resetTape();
			}
			
		}
		
		

	}
	
	public void addTape(int head,char letter) {
		objTur.addTape(head, letter);
	}
	public char readTape(int head) {
		return objTur.readTape(head);
	}
	public void deleteTape(int head) {
		objTur.deleteTape(head);
	}
	public void resetTape() {
		objTur.deleteAll();
	}

}
