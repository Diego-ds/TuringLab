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

	public void deleteTape(int head) {
		if(n==1) {
			c0=null;
			c1=null;
			c2=null;
			n--;
		}
		if(c0!=null) {
			switch(head) {
			case 0:
				c0=c0.getNextTape();
				c0.getNextTape().setPrevTape(null);
				n--;
				if(n%2 !=0) {
					c1=c1.getNextTape();
				}
				break;
			case 1:
				if(n==2) {
					c1.getNextTape().setPrevTape(null);
					c0=c1.getNextTape();
					c1=c1.getNextTape();
					n--;
				}else if(n%2 ==0) {
					Tape next = c1.getNextTape();
					c1.getPrevTape().setNextTape(next);
					next.setPrevTape(c1.getPrevTape());
					c1=next;
					n--;
				}else {
					c1.getPrevTape().setNextTape(c1.getNextTape());
					c1.getNextTape().setPrevTape(c1.getPrevTape());
					c1=c1.getPrevTape();
					n--;
				}
				break;
			case 2:
				c2.getPrevTape().setNextTape(null);
				c2=c2.getPrevTape();
				n--;
				if(n%2 ==0) {
					c1=c1.getPrevTape();
				}
				break;
			}
		}
	}
	
	public char readTape(int head) {
		char tape='0';
		switch(head) {
		case 0:
			if(c0!=null) {
				tape=c0.getLetter();
			}else {
				tape='#';
			}
			break;
		case 1:
			if(c1!=null) {
				tape=c1.getLetter();
			}else {
				tape='#';
			}
			break;
		case 2:
			if(c2!=null) {
				tape=c2.getLetter();
			}else {
				tape='#';
			}
			break;
		}
		return tape;
	}
	
	public void deleteAll() {
		c0=null;
		c1=null;
		c2=null;
		n=0;
	}
}
