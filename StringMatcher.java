package StringMatcher;

class BoyerMoore {
	public static int bmMatch(String text, String pattern) {
                pattern = pattern.toLowerCase();
		text = text.toLowerCase();
                
		int last[] = buildLast(pattern);
		int n = text.length();
		int m = pattern.length();
		int i = m-1;

		if (i > n-1)
			return -1; // no match if pattern is 
                 // longer than text
		int j = m-1;
		
		do {
			if (pattern.charAt(j) == text.charAt(i))
				if (j == 0) {
					return i; // match
				} else { // looking-glass technique
					i--;
					j--;
				} else { // character jump technique
					int lo = last[text.charAt(i)];  //last occ 
					i = i + m - Math.min(j, 1+lo);
					j = m - 1;
				}
		} while (i <= n-1);

		return -1; // no match
	} // end of bmMatch()

	public static int[] buildLast(String pattern) {
	/* Return array storing index of last
	occurrence of each ASCII char in pattern. */
		int last[] = new int[65536]; // ASCII char set

		for(int i=0; i < 65536; i++)
			last[i] = -1; // initialize array

		for (int i = 0; i < pattern.length(); i++)
			last[pattern.charAt(i)] = i; 

		return last;
	} // end of buildLast()
}
	
class KMP {
	public static int kmpMatch(String text, String pattern) {
                pattern = pattern.toLowerCase();
		text = text.toLowerCase();
                
		int n = text.length();
		int m = pattern.length();

		int fail[] = computeFail(pattern);

		int i=0;
		int j=0;
	
		while (i < n) {
			if (pattern.charAt(j) == text.charAt(i)) {
				if (j == m - 1)
					return i - m + 1; // match
				i++;
				j++;
			}
      
		else if (j > 0)
            j = fail[j-1];
		else
			i++;
		} 
    
		return -1; // no match
	} // end of kmpMatch()
	
	public static int[] computeFail(String pattern) {
		int fail[] = new int[pattern.length()]; 
		fail[0] = 0;

		int m = pattern.length();
		int j = 0;
		int i = 1;
	
		while (i < m) {
			if (pattern.charAt(j) == pattern.charAt(i)) {   //j+1 chars match
				fail[i] = j + 1;
				i++;
				j++;
			} else if (j > 0) { // j follows matching prefix
				j = fail[j-1];
			} else {     // no match
				fail[i] = 0;
				i++;
			}
		}
		
		return fail;
	} // end of computeFail()
}

public class StringMatcher {
    public static int MatchString(String text, String pattern, int Algorithm) {
        // Algorithm 1 is KMP, 2 is Boyer-Moore
        int PatternLocation = 0;
                
        if (Algorithm == 1) {
            PatternLocation = KMP.kmpMatch(text, pattern);
        } else if (Algorithm == 2) {
            PatternLocation = BoyerMoore.bmMatch(text,pattern);
        } 
        
        return PatternLocation; 
    }
}
