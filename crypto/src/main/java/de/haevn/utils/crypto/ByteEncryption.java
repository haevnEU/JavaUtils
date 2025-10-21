package de.haevn.utils.crypto;

import javax.crypto.*;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ByteEncryption implements IDataEncryption<ByteEncryption.ByteData> {

    private SecretKey key = null;
    private String algorithm = null;
    record ByteData(byte[] data){}



    @Override
    public void init(SecretKey key, String encryptionAlgorithm) {
        this.key = key;
        this.algorithm = encryptionAlgorithm;
    }

    @Override
    public ByteData encrypt(ByteData data) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if(key == null || algorithm == null){
            throw new IllegalStateException("ByteEncryption not initialized. Call init() first.");
        }


        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.data());

        return new ByteData(encrypted);
    }

    @Override
    public ByteData decrypt(ByteData data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if(key == null || algorithm == null){
            throw new IllegalStateException("ByteEncryption not initialized. Call init() first.");
        }

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedData = cipher.doFinal(data.data());



        return new ByteData(decryptedData);
    }
}
