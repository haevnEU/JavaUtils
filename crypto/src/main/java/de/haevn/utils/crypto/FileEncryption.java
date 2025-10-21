package de.haevn.utils.crypto;

import javax.crypto.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class FileEncryption implements IDataEncryption<File> {

    private final ByteEncryption byteEncryption = new ByteEncryption();


    @Override
    public void init(SecretKey key, String encryptionAlgorithm) {
        byteEncryption.init(key, encryptionAlgorithm);
    }

    @Override
    public File encrypt(File file) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        final byte[] data = Files.readAllBytes(file.toPath());
        final byte[] encrypted = byteEncryption.encrypt(new ByteEncryption.ByteData(data)).data();
        Files.write(file.toPath(), encrypted);
        return file;
    }

    @Override
    public File decrypt(File file) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final byte[] data = Files.readAllBytes(file.toPath());
        final byte[] encrypted = byteEncryption.decrypt(new ByteEncryption.ByteData(data)).data();
        Files.write(file.toPath(), encrypted);
        return file;
    }
}
