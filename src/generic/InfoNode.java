package generic;

import java.sql.Timestamp;

public class InfoNode {
	
	Timestamp ts;
	String  text;
	long id;
	public InfoNode(Timestamp ts,long id,String text) {
		this.ts=ts;
		this.id=id;
		this.text=text;
	}
}

//public class InfoNode implements Comparable{
//	
//	Timestamp ts;
//	String  text;
//	long id;
//	public InfoNode(Timestamp ts,long id,String text) {
//		this.ts=ts;
//		this.id=id;
//		this.text=text;
//	}
//	@Override
//	public int compareTo(Object o) {
//		int i = this.ts.compareTo(o.ts2);
//		return i;
//	}
//}