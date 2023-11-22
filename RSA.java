import java.util.Random;
import java.math.BigInteger;
import java.util.*;
import java.security.*; //for keyPair
public class RSA {

    public static int[] LCG (int seed,int quantity){
      int mod= 65537 ; //must be larger than both inc and multiplier
       int multiplier=75;
       int inc= 74 ;
            int[] randomNumbers = new int[quantity];
            
    
            for (int i = 0; i < quantity; i++) {
                seed = (multiplier * seed + inc) % mod; //seed value is changing each time 
                if(seed<0)
                seed=seed*(-1);//to make all results postive
                randomNumbers[i] = seed ;
            }
    
            return randomNumbers;
        }
   
//prime means probably prime but composite means for sure not prime
     public static boolean millerRabinTest(long n,int k){
        if (n <= 1) return false; //prime must be greater than one
        if (n == 2 || n == 3) return true; //we know 2 and 3 are prime
        if (n % 2 == 0) return false; //any even numer is composite
         // step one find n-1 and write it  as  2^r * d 
         long d = n - 1;
         int r = 0;
         //bcz r and d must be whole numbers d will be an odd number and r will be largest non negative
         while (d % 2 == 0) {
             d /= 2;
             r++;
         }
         //pick an a 1<a<n-1
          Random random = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + random.nextInt((int) (n - 3)); // Random a in the range [2, n-2]
            long x = power(a, d, n);
            if(x== 1||x==n-1) continue; //x=1 or -1 only return true after checking all iterations note:n-1 is congruent to -1 in mod n
            //not 1 nor -1
            //calc x^2jmodn
            for (int j = 1; j < r; j++) {
                x = (x * x) % n;
                if (x == n - 1) return true;//-1 probably prime
            }
            if(x==1) return false; //after first time x=1 is composite

    }
    return true;//checked all iterations
}
    public static KeyPair generateKeys() throws NoSuchAlgorithmException{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");//create
        keyPairGen.initialize(2048);//initialize
        //KeyPair pairOfKeys = keyPairGen.generateKeyPair();
        //PrivateKey privKey = pairOfKeys.getPrivate();
        //PublicKey pubKey = pairOfKeys.getPublic();
        return keyPairGen.generateKeyPair();
    }
//square-and-multiply algorithm for modular exponentiation. 
    //to compute a^dmodn
    private static long power(long base, long exponent, long modulus) {//i think modularExponentiation should be used here instead
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) { //least significant  bit of exponent is 1
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent /= 2;
        }
        return result;
    }

     public static int[] String_to_intArray(String n){
        int[] result = new int[n.length()];

        for (int i = 0; i < n.length(); i++) {
            char C = n.charAt(i);
            int ascii = ((int) C) - ((int) 'a')+1; //ascii value for char - ASCCI of a +1 so that a will be 1 instead of 97
            result[i] = ascii;
        }

        return result;
    }
    


   // Method to find the modular multiplicative inverse of 'a' under modulo 'm'
   static int extendedEuclideanAlgorithm(int a, int m) {
    
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



   
       
   public static long[] encrypt(String message, long e, long n) {
        long[] encryptedMessage = new long[message.length()]; // stores the encrypted message

        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            
            // Encrypt each character using the RSA algorithm
           BigInteger encryptedChar = BigInteger.valueOf(character).pow((int) e).mod(BigInteger.valueOf(n));          
            encryptedMessage[i] = encryptedChar.longValue();
        }
        
        return encryptedMessage;
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

    static long modularExponentiation(long base, long exponent, long modulus){
        long result=1;//main idea: (m * n) % p =((m % p) * (n % p)) % p
        while(exponent> 0) {
              //if y is odd, multiply x with result
           if ((exponent & 1) != 0)
              result = result * base;
              // y is even now
           exponent = exponent >> 1; //shifting to the right aka dividing by 2 but in binary sense
           base = base * base; //x -> x^2
        }
        do{
           result=result % modulus;
           if(result<0)
              result+=modulus;
        }while(result<0);
        
        return result;
            }

        


public static void main(String[] args) {
    
    int[] randomNumbers = LCG(40843, 100); //generate 9 pseudorandom numbers

    // Print the generated random numbers and check primality
    for (int i = 0; i < randomNumbers.length; i++) {

        boolean isPrime = millerRabinTest(randomNumbers[i], 5); //run test 5 times

            if (isPrime) {
                System.out.println(randomNumbers[i] + " is probably prime.");
            } else {
                System.out.println(randomNumbers[i] + " is composite.");
            }
           
}
//KEYS
try {//throws no such algorithom
    KeyPair keyPair = generateKeys();
    PrivateKey privKey = keyPair.getPrivate();
    PublicKey pubKey = keyPair.getPublic();

    System.out.println("Public Key: " + pubKey);
    System.out.println("Private Key: " + privKey);
} catch (NoSuchAlgorithmException e) {
    e.printStackTrace();
}

String inputString = "hello";
        int[] resultArray = String_to_intArray(inputString);

        System.out.print("Result: [");
        for (int i = 0; i < resultArray.length; i++) {
            System.out.print(resultArray[i]);
            if (i < resultArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

//////////// idk if its the right place

int a= 700; // not the same values as the sample run bc idk what they are
int m = 8464;
//System.out.println("I called extendedEuclideanAlgorithm, and got d to be: " , extendedEuclideanAlgorithm(a, m));
        


        String plaintext = "Norah";
        long e = 65537;  // Public key exponent
        long n = 131071;    // Public key modulus

       
        long[] encrypted = encrypt(plaintext, e, n);

               System.out.println(" This is encrypt method converting my string to int:");
        for (int i=0 ; i<encrypted.length ; i++ ) {
            System.out.print(encrypted[i]+ " ");
}


}//end main
}//end class
 