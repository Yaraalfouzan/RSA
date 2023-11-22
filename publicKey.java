//import java.math.BigInteger;

public class publicKey {
    private int modulus;
    private int exponent;
    public publicKey(int modulus, int exponent) {
        this.modulus = modulus;
        this.exponent = exponent;
    }

    public int getModulus() {
        return modulus;
    }

    public int getExponent() {
        return exponent;
    }
}
