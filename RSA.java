import java.util.Random;

public class RSA {

    public static int[] LCG (int seed,int quantity){
      int mod= 9;//(int) Math.pow(2, 32) ; //must be larger than both inc and multiplier
       int multiplier=7;//1664525;
       int inc= 4 ;//1013904223 ;
            int[] randomNumbers = new int[quantity];
            
    
            for (int i = 0; i < quantity; i++) {
                seed = (multiplier * seed + inc) % mod; //seed value is changing each time 
                randomNumbers[i] = seed ;
            }
    
            return randomNumbers;
        }
   
//prime means probably prime but composite means for sure not prime
     public static boolean millerRabinTest(long n,int k){
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0) return false;
         // find n-1 and write it  as  2^r * d 
         long d = n - 1;
         int r = 0;
         //bcz r and d must be whole numbers
         while (d % 2 == 0) {
             d /= 2;
             r++;
         }
         //pick an a 1<a<n-1
          Random random = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + random.nextInt((int) (n - 3)); // Random a in the range [2, n-2]
            long x = power(a, d, n);
            if(x== 1||x==n-1) continue; //x=1 or -1 only return true after checking all iterations
            //not 1 nor -1
            for (int j = 1; j < r; j++) {
                x = (x * x) % n;
                if (x == n - 1) return true;//-1 probably prime
            }
            if(x==1) return false; //after first time x=1 is composite

    }
    return true;//checked all iterations
}
    //to compute a^dmodn
    private static long power(long base, long exponent, long modulus) {
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent /= 2;
        }
        return result;
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


<<<<<<< HEAD
   public  long[] encrypt(String message, int e, int n){
       
   }

public static void main(String[] args) {
    
    int[] randomNumbers = LCG(3, 9); //generate 9 pseudorandom numbers

    // Print the generated random numbers and check primality
    for (int i = 0; i < randomNumbers.length; i++) {

        boolean isPrime = millerRabinTest(randomNumbers[i], 5); //run test 5 times

            if (isPrime) {
                System.out.println(randomNumbers[i] + " is probably prime.");
            } else {
                System.out.println(randomNumbers[i] + " is composite.");
            }
}
}

=======
   public  long[] encrypt(String message, int e, int n){}

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



>>>>>>> 577ef81b655a992f6bfac89a71e82697bf49941a
