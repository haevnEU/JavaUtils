package de.haevn.utils.crypto;

public interface Algorithm {

    /**
     * <h2>OTP</h2>
     * <p>Enumeration of the supported One Time Password (OTP) encryption algorithms.</p>
     */
    enum OTP {
        /**
         * @deprecated This is a deprecated algorithm and should not be used.
         */
        SHA1("HmacSHA1", "SHA-1"),
        SHA256("HmacSHA256", "SHA-256"),
        SHA512("HmacSHA512", "SHA-512");

        final String algorithm;
        final String algorithmName;

        OTP(String algorithm, String algorithmName) {
            this.algorithm = algorithm;
            this.algorithmName = algorithmName;
        }
    }

    enum Encryption {
        AES, DES, RSA
    }


}
