import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
    public static void main (String [] args)
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
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // Tell the object to encrypt and insert the key and initialization vector
        cipher.init(Cipher.ENCRYPT_MODE,theKey,initialVector);

        //Plaintext converted to byte array
        String plaintext = "Welcome to MATH 314!";
        byte [] binaryPlaintext = plaintext.getBytes("UTF-8");

        // Encryption of the plaintext to cipher text
        byte [] ciphertext = cipher.doFinal(binaryPlaintext);

        // Convert the ciphertext into a hexadecimal string
        String encryptedMessage = bytesToHex(ciphertext);

        System.out.println(encryptedMessage);
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

    // Method converts a byte array into a hex string
    // Code of method found Mkyong.com
    private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
