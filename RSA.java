public class RSA {

    public int[] LCG (int seed,int quantity){

    }

    public boolean millerRabinTest(long n,int k){

    }

    public int[] String_to_intArray(String n){

    }


   public long extendedEuclideanAlgorithm(long e, long m){

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
}

