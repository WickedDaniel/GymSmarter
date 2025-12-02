package Modelo.Utils;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;

// Autor: Narayan Subedi
// https://stackoverflow.com/questions/3479067/how-to-decode-with-messagedigest-base64
public class Encriptador {
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String ENCRYPTION_KEY = "ENCRYPTION_KEY";

    private static KeySpec keySpec;
    private static SecretKeyFactory secretKeyFactory;
    private static Cipher cipher;
    private static SecretKey secretKey;

    // Bloque de inicialización estático
    static {
        try {
            byte[] arrayBytes = ENCRYPTION_KEY.getBytes(UNICODE_FORMAT);
            keySpec = new DESedeKeySpec(arrayBytes);
            secretKeyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            secretKey = secretKeyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar el encriptador: " + e.getMessage(), e);
        }
    }

    private Encriptador() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no debe ser instanciada");
    }

    public static String encriptar(String textoPlano) {
        if (textoPlano == null || textoPlano.isEmpty()) {
            return null;
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plainText = textoPlano.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            return Base64.getEncoder().encodeToString(encryptedText);
        } catch (Exception e) {
            System.err.println("Error al encriptar: " + e.getMessage());
            return null;
        }
    }

    public static String desencriptar(String textoEncriptado) {
        if (textoEncriptado == null || textoEncriptado.isEmpty()) {
            return null;
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedText = Base64.getDecoder().decode(textoEncriptado);
            byte[] plainText = cipher.doFinal(encryptedText);
            return new String(plainText, UNICODE_FORMAT);
        } catch (Exception e) {
            System.err.println("Error al desencriptar: " + e.getMessage());
            return null;
        }
    }
    public static boolean verificar(String textoPlano, String textoEncriptado) {
        if (textoPlano == null || textoEncriptado == null) {
            return false;
        }

        String textoPlanoDesencriptado = desencriptar(textoEncriptado);
        return textoPlano.equals(textoPlanoDesencriptado);
    }

    public static boolean validarFortaleza(String contrasena) {
        if (contrasena == null || contrasena.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;

        for (char c : contrasena.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            if (Character.isLowerCase(c)) tieneMinuscula = true;
            if (Character.isDigit(c)) tieneNumero = true;
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero;
    }

    public static String obtenerRequisitosFortaleza() {
        return "La contraseña debe tener:\n" +
                "- Mínimo 8 caracteres\n" +
                "- Al menos una letra mayúscula\n" +
                "- Al menos una letra minúscula\n" +
                "- Al menos un número";
    }
}