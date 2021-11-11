package table;

public class TestClass{

	public static void main(String[] args) {
	
	HashTableLin a = new HashTableLin(11,1);
	
	
	System.out.println(a.getTableSize());
	
	HashTableLin b = new HashTableLin(100,0.5);
	
	
	System.out.println(b.getTableSize());
	
	HashTableLin c = new HashTableLin(999,0.2);
	
	
	System.out.println(c.getTableSize());
	
	HashTableLin d = new HashTableLin(1,1);
	
	
	System.out.println(d.getTableSize());
	
	}		

}
