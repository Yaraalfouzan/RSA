import java.util.Random;
import java.math.BigInteger;
import java.security.*;
import java.util.*;

public class RSA {

    public static int[] LCG (int seed,int quantity){
    int mod= 65537 ; //must be larger than both inc and multiplier
    int inc= 74 ;
    int multiplier=75;
                System.out.println("Hi there this is LCG method i've been called with seed "+seed+" & quantinty "+quantity+"\nand I have those initialized local variables:\n(A ="+multiplier+"  C = "+ inc  +" M = "+mod+")");
                int[] randomNumbers = new int[quantity];
            
    
            for (int i = 0; i < quantity; i++) 
            {
                seed = (multiplier * seed + inc) % mod; //seed value is changing each time 
                if(seed<0)
                seed=seed*(-1);//to make all results postive
                randomNumbers[i] = seed ;
            }
            
            System.out.println("I generated 100 random numbers, and I made them all positives!\n");
            // Print the generated random numbers and check primality
            for (int i = 0; i < 10; i++) 
            {
                System.out.print(randomNumbers[i]+",");         
            }//end for

                System.out.print("...");
                System.out.println("Bye now\n -LCG");
            
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
         
         while (d % 2 == 0)
         {
             d /= 2;
             r++;
         }
        
         //pick an a 1<a<n-1
          Random random = new Random();
          outerLoop:
        for (int i = 0; i < k; i++) {
            long a = 2 + random.nextInt((int) (n - 3)); // Random a in the range [2, n-2]
            long x = modularExponentiation(a, d, n); //to compute a^dmodn
                
            if(x== 1||x==n-1) continue; //x=1 or -1 only return true after checking all iterations note:n-1 is congruent to -1 in mod n
            //not 1 nor -1
            //calc x^2jmodn
                for (int j = 1; j < r; j++) {
                     x = (x * x) % n;
                     if (x == n - 1){
                    continue outerLoop;// return true;           //-1 probably prime
                 }
        }
            return false; //after first time x=1 is composite

    }
    return true;//checked all iterations
}
    

    public static keyPair generateKeys(){
                System.out.println("Hola i'm generate key, i will be generating 100 random numbers using LCG method\nCalling LCG");
                int[] randomNum=LCG(40843,100);
                System.out.println("Back to generate keys, now i will examine random number and assign p to the first number that passes miller robin test, q to the second number it's not equal to p obv");
                int i=0;
                boolean isPrime=false;
                
                            while(!isPrime)
                            {
                        isPrime=millerRabinTest(randomNum[i],5);
                        
                        i++;
                            }
                
            int p=randomNum[--i];
            System.out.println("p is "+p+" this is element number "+(i+1)+ " in the random list");
            isPrime=false;
            i++;
            int q;

                            do{
                            while(!isPrime)
                            {
                            isPrime=millerRabinTest(randomNum[i],5);
                            i++;
                            
                            }

                            q=randomNum[--i];
                            }while(q==p);//rechoose q if it turns out equal to p


            System.out.println("q is "+q+" this is element number "+(i+1)+ " in the random list");
            int phi=(p-1)*(q-1);
            System.out.println("I calcualte phi "+phi);
            Random random = new Random();
            int e;

                            do {
                            e = 2 + random.nextInt(phi - 2);

                            } while (gcd(e, phi) != 1);

            System.out.println("I set e: "+ e);

            int d=extendedEuclideanAlgorithm(e, phi);  
            System.out.println("I called extendedEuclideanAlgorithm, and got d to be "+d);
            
            int n=p*q;
            
            System.out.println("finally, I am creating an instance of KeyPair class as:\nKeyPair(new PublicKey(n, e), new PrivateKey(n, d))\nand returning it. Bye now! --generateKeys method");
            
            publicKey pubKey=new publicKey(n, e);
            privateKey privKey=new privateKey(n, d);
            keyPair myKeyPair=new keyPair(pubKey,privKey);
                
            return myKeyPair;
            }

    
    
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

  

    
    
   // Method to find the modular multiplicative inverse of 'a' under modulo 'm'
        static int extendedEuclideanAlgorithm(int a, int m) {
            //  ((a)) mod m, s.a a=dq+r
        
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
            System.out.println("Hi there! This is encrypt method\nconverting my string to int:");
                long[] encryptedMessage = new long[message.length()]; // stores the encrypted message
                long[] longMessage=String_to_longArray(message);
                System.out.println("\nEncrypted values:\n");
                    for (int i = 0; i < message.length(); i++) {
                        
                    // Encrypt each character using the RSA algorithm
                        long encryptedChar=modularExponentiation(longMessage[i], e, n);
                    //BigInteger encryptedChar = BigInteger.valueOf(character).pow((int) e).mod(BigInteger.valueOf(n));          
                        encryptedMessage[i] = encryptedChar;
                        System.out.print(encryptedMessage[i]+" ");
                    }
                System.out.println("\nBye now! -encrypt method");
                return encryptedMessage;
                }

    
    
    public static String decrypt(long[] ciphertext, long d, long n){
            long[] partiallyDecrypted=new long[ciphertext.length];
            System.out.println("Hi there! its the dycrypt method\ndecrypted values: ");
                
                for( int i = 0; i<ciphertext.length;i++){
                    
                long num = modularExponentiation(ciphertext[i],d,n);
                partiallyDecrypted[i]=num;
                System.out.print(partiallyDecrypted[i]+" ");
                }

            String decrypted=Array_to_String(partiallyDecrypted);
            System.out.println("Array to string: "+decrypted+"\nByeee -decrypted");
            return decrypted;
            }


   public static long[] String_to_longArray(String n){
        long[] result = new long[n.length()];
            for (int i = 0; i < n.length(); i++) {
                char C = n.charAt(i);
                long ascii = ((long) C) - ((long) 'a')+1; //ascii value for char - ASCCI of a +1 so that a will be 1 instead of 97
                result[i] = ascii;
                System.out.print(result[i]+" ");
            }
        return result;
    }
    
    
    static String Array_to_String(long[] a) {
        StringBuilder str = new StringBuilder();
            for (int i = 0; i < a.length; i++) {
                str.append(longToString(a[i]));
            }
        return str.toString();
    }

    
    
    static String longToString(long n) {
        char alphabetChar = (char) ('A' + n - 1);
        return String.valueOf(alphabetChar);
    }

    
    
    static long modularExponentiation(long base, long exponent, long modulus) {
                long result = 1;
            
                while (exponent > 0) {
                    if ((exponent & 1) != 0)
                        result = (result * base) % modulus;
            
                    exponent = exponent >> 1;
                    base = (base * base) % modulus;
                }
            
                do {
                    result = result % modulus;
                    if (result < 0)
                        result += modulus;
                } while (result < 0);
            
                return result;
            }
            
                   

        


public static void main(String[] args) {
    System.out.println("Hi there! This is the main method\nCalling generateKeys");
    keyPair mainKeysPair=generateKeys();
    String plaintext = "hello";
    System.out.println("Setting plain text to: "+plaintext+"\nCalling Encrypt");
    long[] ciphertext=encrypt(plaintext,mainKeysPair.getPublicKey().getExponent(),mainKeysPair.getPublicKey().getModulus());
    System.out.println("calling decrypt on encrypt output");
    String decrypted=decrypt(ciphertext,mainKeysPair.getPrivateKey().getExponent(),mainKeysPair.getPrivateKey().getModulus());//n is modulus
    System.out.println("making sure decryptedText and plaintext are equalsIgnoreCase...  ");
    if(decrypted.equalsIgnoreCase(plaintext)){
        System.out.println("yes they are equal");
    } else{
        System.out.println("No they are not equal :(");
    }
    System.out.println("Bye now forever");





}//end main
}//end class
 