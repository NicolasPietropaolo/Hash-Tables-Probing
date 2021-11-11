package table;

import java.lang.Math;
import java.util.Arrays;

public class HashTableLin {//all variable declarations
	int[] table;
	
	private int tableSize;
	private int numOfKeys;
	private double loadFactor;
	
	
	//define all the getters
	public int getTableSize() {
		return tableSize;
	}
	
	public int getNumKeys() {
		return numOfKeys;
	}
	
	public double getMaxLoadFactor() {
		return loadFactor;
	}
	
	public HashTableLin(int maxNum, double load) {
		
		tableSize = (int) Math.ceil(maxNum/load); //define size of table
		loadFactor = load; //store load in loadFactor
		if(isPrime(tableSize) == false) 
			tableSize = nextPrime(tableSize); 
		table= new int [tableSize]; //make the table an array of tableSize
		Arrays.fill(table,-1);//empty table
	
		
		}
	
	
	static boolean isPrime(int n)
    {
        // Corner cases
        if (n < 1) return false;
        if (n <= 3) return true;
         
        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;
         
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
            return false;
         
        return true;
    }
	
	// Function to return the smallest
    // prime number greater than N
    static int nextPrime(int N)
    {
     
        // Base case
        if (N <= 1)
            return 2;
     
        int prime = N;
        boolean found = false;
     
        // Loop continuously until isPrime returns
        // true for a number greater than n
        while (!found)
        {
            prime++;
     
            if (isPrime(prime))
                found = true;
        }
     
        return prime;
    }
	
    public void insert(int n) {
    	int key = n%tableSize; //find the key
    	
    	if(numOfKeys == (int)(tableSize*loadFactor)) {//capacity of hash Table doing linear probing
    		this.rehash();
    	}
    	for(int i =0; i<tableSize;i++) {//for loop goe through table size for linear probing
    		if(table[key] == -1){//no collisions
    			table[key] = n; //place value in correct key
    			numOfKeys++; //increase key amount
    			break; //leave loop after key is filled
    		}
    	
    		else {//if the key is already filled
    			
    			if(table[key] == n) {//if key is filled with same value break out of loop
    				break;
    			}
        		if(key+1>=tableSize) { //go back to 0 key of hash table if key becomes larger than the hash table
        			key = 0;
        			}
        		
        		else {
        			key++; //increase key by 1	
        			}		
        	}
    		
    	}
    }

	private void rehash() {
		//define temp variables for size and table to not redefine original values 
		int tempsize = tableSize;
		int[] temptable = table;
		//redefine the size of the table to next prime number after doubling original size
		tableSize = (tableSize*2);
		if(isPrime(tableSize) == false) //check that value is not already prime number
			tableSize = nextPrime(tableSize);
		table= new int [tableSize]; //make the table an array of tableSize
		Arrays.fill(table,-1);//empty table
		
		numOfKeys = 0; //reset keys to 0
		
		for(int i =0; i<tempsize;i++) {
			if(temptable[i]!= -1) {//found a value that needs to be inserted into the table
				this.insert(temptable[i]);//call insert to correctly place value
				
			}
		}
		
	}
    
	public boolean isIn(int n) {
		
		int key = n%tableSize; //find the key
    	
    	
    	for(int i =0; i<tableSize;i++) {//for loop goes through table size for linear probing
    		if(table[key] == -1){//no collisions- no key
    			return false;
    		}
    	
    		else {//if the key is already filled
    			
    			if(table[key] == n) {//if key is filled with same value break out of loop
    				return true;
    			}
        		if(key+1>=tableSize){ //go back to 0 key of hash table if key becomes larger than the hash table
        			key = 0;
        		}
        		else {
        				key++;
        		}
        	}
    		
    	}
		return false; //for loop ran and no key was found so return false
	}
    
    public void printKeys() {
    	
    	for(int i=0;i< tableSize;i++) {//for loop goes through table
			if(table[i]!=-1) {//if no collisions are present at the element of the table
				System.out.print(table[i] + ","); //print element with comma
			}
		}
		
	}
    
	public void printKeysAndIndexes() {
		for(int i =0;i<tableSize;i++) {//for loop goes through table
			if(table[i]!=-1) {//if no collisions are present at the element of the table
				System.out.print(table[i] + "("+i+"), "); //prints key and its array index
			}
		}
	
	}
    
    public int insertCount(int n) {
    	int key = n%tableSize; //find the key
    	int count = 0; //number of collisions
    	
    	
    	if(numOfKeys == (int)(tableSize*loadFactor)) {//capacity of hash Table doing linear probing
    		this.rehash();
    	}
    	for(int i =0; i<tableSize;i++) {//for loop goe through table size for linear probing
    		if(table[key] == -1){//no collisions
    			table[key] = n; //place value in correct key
    			numOfKeys++; //increase key amount
    			break; //leave loop after key is filled
    		}
    	
    		else {//if the key is already filled
    			
    			if(table[key] == n) {//if key is filled with same value break out of loop
    				break;
    			}
    			count++; //increase collisions by 1	
        		if(key+1>=tableSize) { //go back to 0 key of hash table if key becomes larger than the hash table
        			key = 0;
        			
        			}
        		
        		else {
        			key++; //increase key by 1	
        			
        			}	
        	}
    		
    	}
   
    
    		return count+1; //return number of probes which is one more than collisions occurred
    
    }
    
    public int searchCount(int n) {
    	int key = n%tableSize; //find the key
    	int count =0;
    	
    	
    	for(int i =0; i<tableSize;i++) {//for loop goes through table size for linear probing
    		if(table[key] == -1){//no collisions- no key
    			break;
    		}
    	
    		else {//if the key is already filled
    			
    			if(table[key] == n) {//if key is filled with same value break out of loop
    				break;
    			}
    			count++;
        		if(key+1>=tableSize){ //go back to 0 key of hash table if key becomes larger than the hash table
        			key = 0;
        		}
        		else {
        				key++; //go through elements
        		}
        	}
    		
    	}
		return count+1 ; //number of probes
    }
	
}
