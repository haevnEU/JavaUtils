package de.haevn.utils.crypto;

import javax.crypto.SecretKey;

public class StreamEncryption implements IDataEncryption<Void> {
    @Override
    public void init(SecretKey key, String encryptionAlgorithm) {

    }

    @Override
    public Void encrypt(Void data) {
        // Implement file encryption logic here
        return null;
    }

    @Override
    public Void decrypt(Void data) {
        return null;
    }


}
