package model;

public class Tape {
	char letter;
	private Tape nextTape;
	private Tape prevTape;
	public Tape(char l) {
		this.letter=l;
		this.nextTape=null;
		this.prevTape=null;
	}
	public Tape getNextTape() {
		return nextTape;
	}
	public void setNextTape(Tape nextTape) {
		this.nextTape = nextTape;
	}
	public Tape getPrevTape() {
		return prevTape;
	}
	public void setPrevTape(Tape prevTape) {
		this.prevTape = prevTape;
	}
	
}
