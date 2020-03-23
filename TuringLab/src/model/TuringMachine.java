package model;

public class TuringMachine {
	Tape c0;
	Tape c1;
	Tape c2;
	int n;
	public TuringMachine() {
		n=0;
		c0=null;
		c1=null;
		c2=null;
	}
	
	public void addTape(int head,char letter) {
		Tape toAdd = new Tape(letter);
		if(c0==null) {
			c0=toAdd;
			c1=toAdd;
			c2=toAdd;
			n++;
		}else if(c1.getPrevTape()==null && head==1) {
			c1.setPrevTape(toAdd);
			toAdd.setPrevTape(c1);
			c1=toAdd;
			c0=toAdd;
			n++;	
		}else {
			switch(head) {
			case 0:
				c0.setPrevTape(toAdd);
				toAdd.setNextTape(c0);
				c0=toAdd;
				n++;
				if(n%2 == 0) {
					c1=c1.getPrevTape();
				}
				break;
			case 1:
				if(n%2==0) {
					Tape next =c1.getNextTape();
					c1.setNextTape(toAdd);
					toAdd.setPrevTape(c1);
					toAdd.setNextTape(next);
					next.setPrevTape(toAdd);
					c1=toAdd;
					n++;
				}else {
					Tape prev =c1.getPrevTape();
					c1.setPrevTape(toAdd);
					toAdd.setNextTape(c1);
					toAdd.setPrevTape(prev);
					prev.setNextTape(toAdd);
					c1=toAdd;
					n++;	
				}
				break;
			case 2:
				c2.setNextTape(toAdd);
				toAdd.setPrevTape(c2);
				c2=toAdd;
				n++;
				if(n%2 != 0) {
					c1=c1.getNextTape();
				}
				break;
			}
		}
	}
}
