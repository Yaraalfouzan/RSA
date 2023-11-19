public class RSA {

    public int[] LCG (int seed,int quantity){

    }

    public boolean millerRabinTest(long n,int k){

    }

    public int[] String_to_intArray(String n){

    }


   public long extendedEuclideanAlgorithm(long a, long m){
    
    // we are trying to find the inverse of ((a)) mod m, s.a a=dq+r
    
    int m0 = m;  // Store the original value of 'm' 

    // Initialize 'x' and 'y' for the iterative process
    int x = 1, y = 0;

    // Iterate using the Extended Euclidean Algorithm until 'a' becomes 1 ,
            while (a > 1) {
        int q = a / m;  // Calculate quotient 'q' 

        int t = m;      // Store the current value of 'm' 

        // Update 'm' and 'a' for the (next) iteration
        m = a % m;
        a = t;

        t = y;          // Store the current value of 'y'

        // Update 'y' and 'x' based on the (current) iterative formula
        y = x - q * y; 
        x = t;
    }

    if (x < 0)
        x += m0;        // If 'x' is negative, convert it to its positive equivalent

    if (x == 0)
        return 0;       // Return 0 if the modular inverse doesn't exist
    else
        return x;       // Return the modular multiplicative inverse 'x'


   }


   public  long[] encrypt(String message, int e, int n)
}
 static String IntArray_to_String(int[] a) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            str.append(intToString(a[i]));
        }
        return str.toString();
    }

    static String intToString(int n) {
        char alphabetChar = (char) ('A' + n - 1);
        return String.valueOf(alphabetChar);
    }



