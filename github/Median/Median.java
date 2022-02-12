public class Median {    
    class IndexedArray {
    	final int[] nums;
        int start=0;
        int end;
        IndexedArray(int[] input) {
        	if (input == null) throw new IllegalArgumentException("Bad input");//ArgumentException
    		nums =  input;
    		end = input.length - 1;
	}
	public int get(int index) { return nums[index]; }
	public int numslength() { return nums.length; }
	public int firstMore(double d) {
		return numCond(d, false);
	}
	public int firstNonLess(double d) {
		return numCond(d, true);
	}
	private int numCond(double d, boolean eq) {
		int count = 0;
		for (int i=0; i<nums.length; i++) {
			if (nums[i] < d) count++;
			else if (eq && nums[i] == d) count++;
		}
		return count;
	}
	
	public String print() {
		String s = "";
		for (int i=start; i <=end; i++) {
			s += nums[i] + ",";
		}
		return s;
	}
    } 


    public IndexedArray A, B;
    public Median() {
	int[] arr1 = {14,16,17, 80};
	int[] arr2 = {1,15};
	A = new IndexedArray(arr1);
	B = new IndexedArray(arr2);
    }

public static void main(String[] args) {
        Median m = new Median();
    	Double median = m.findMed(m.A, 0, m.A.numslength() - 1, true, m.B);
    	if (median != null) {
    		System.out.println("Found in A: " + median);
    	}
    	else	System.out.println("Something is terribly wrong!");
}

public double crossArrNext(IndexedArray arr, double d, boolean valFirst) {
	int otherdex = (valFirst) ? arr.firstNonLess(d) : arr.firstMore(d);
	return ( d + arr.get(otherdex) ) /2;
}

public Double findMed(IndexedArray A, int start, int end, Boolean aIsFirst, IndexedArray B) {
    System.out.println(A.print() + " ," + B.print() + ", start "+start+",end "+end+", first?"+aIsFirst);
    int lensum = (A.numslength() + B.numslength());
    int haf = lensum / 2;
    boolean midIsExact = lensum % 2 == 1;	
    if (end < start) {
    	if (aIsFirst) {
    	        System.out.println("SWAAAPP!");
    		return findMed(B, 0, B.numslength() - 1, false, A); 
    	}
    	return null; 
    }
    int adex = (start + end)/2;
    double item = (double)(A.get(adex));
    double v = aIsFirst ? item : start < end ? ((double)(item + A.get(adex + 1))/2) : crossArrNext(B, item, aIsFirst);
    
    int bdex = (aIsFirst) ? B.firstMore(v) : B.firstNonLess(v);
    int lessers = adex + bdex;// + ((end - start) % 2 == 1 ? 0 : 1);
    System.out.println("adex="+adex+" ,bdex= "+bdex+" lessers=" +lessers+" valll:"+v);
    if (lessers == haf) {
    	return (midIsExact || start == end) ? v : (((double)A.get(adex - 1) + item)/2);
    }
    
    if (lessers < haf) {
    	return findMed(A, adex + 1, end, aIsFirst, B);
    }
    
    return findMed(A, start, (midIsExact) ? adex - 1 : (start==end) ? adex - 1 : adex, aIsFirst, B);
}
}
