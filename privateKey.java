//import java.math.BigInteger;
public class privateKey {
    private int modulus;
    private int exponent;

    public privateKey(int modulus, int exponent) {
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
