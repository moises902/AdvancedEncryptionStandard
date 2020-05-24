import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESDecryption
{
    public static void main(String [] args)
        // Exceptions for security and crypto libraries
            throws java.security.NoSuchAlgorithmException, javax.crypto.NoSuchPaddingException,
            java.security.InvalidAlgorithmParameterException, java.security.InvalidKeyException,
            java.io.UnsupportedEncodingException, javax.crypto.IllegalBlockSizeException,
            javax.crypto.BadPaddingException
    {
        String key = "61e574f84c44047f7111662c86490067aa95c1dc17211e1897b859e50f072311";
        String iv = "afd12b66f89d1fc2fbb2ac7c56878df8";

        // Converted hexadecimal string of key and iv into byte array
        byte [] binaryKey = hexToByteArray(key);
        byte [] binaryInitialVec = hexToByteArray(iv);

        // Instantiate the initial vector
        IvParameterSpec initialVector = new IvParameterSpec(binaryInitialVec);

        // Instantiate the key
        SecretKeySpec theKey = new SecretKeySpec(binaryKey,"AES");

        // Creating the cipher object that is for AES using CBC
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        // Tell the object to decrypt and insert the key and initialization vector
        cipher.init(Cipher.DECRYPT_MODE,theKey,initialVector);

        String ciphertext = "6413e3092791f0573f7edd5f2dadb812925da416bcf846af64524bdd3ea2938b";

        // Converted the ciphertext into bytes
        byte [] binaryCiphertext = hexToByteArray(ciphertext);

        // Decryption of the ciphertext into a string
        String plaintext = new String (cipher.doFinal(binaryCiphertext), "UTF-8");

        System.out.println(plaintext);
    }

    // Method converts a hexadecimal string into a byte array
    // Code of method found on stackoverflow.com
    public static byte[] hexToByteArray(String hexadecimal)
    {
        int len = hexadecimal.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexadecimal.charAt(i), 16) << 4)
                    + Character.digit(hexadecimal.charAt(i+1), 16));
        }

        return data;
    }
}
