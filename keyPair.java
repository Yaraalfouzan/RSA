public class keyPair {
    private publicKey publicKey;
    private privateKey privateKey;

    public keyPair(publicKey publicKey, privateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public publicKey getPublicKey() {
        return publicKey;
    }

    public privateKey getPrivateKey() {
        return privateKey;
    }
}
