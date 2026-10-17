package renathyu0015.proyectojavafx.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    // Método general: recibe un texto y el nombre del algoritmo ("MD5", "SHA-1", etc.)
    public static String getHash(String texto, String algoritmo) {
        try {
            // 1. Pedimos a Java una "máquina" que sabe calcular ese algoritmo
            MessageDigest md = MessageDigest.getInstance(algoritmo);

            // 2. Convertimos el texto a bytes y calculamos el hash (MD5 siempre da 16 bytes)
            byte[] bytes = md.digest(texto.getBytes(StandardCharsets.UTF_8));

            // 3. Convertimos cada byte a 2 caracteres hexadecimales en minúscula
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            // 16 bytes x 2 caracteres = 32 caracteres
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo no disponible: " + algoritmo, e);
        }
    }

    // Atajo para MD5, que es lo que usaremos en el login
    public static String md5(String texto) {
        return getHash(texto, "MD5");
    }
}