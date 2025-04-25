package com.ifbaiano.estagioinclusivo.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SenhaUtils {

    private static final int ITERACOES = 65536;
    private static final int COMPRIMENTO_CHAVE = 256;
    private static final String ALGORITMO = "PBKDF2WithHmacSHA256";

    public static String gerarSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String gerarHashSenha(String senha, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(senha.toCharArray(), Base64.getDecoder().decode(salt), ITERACOES, COMPRIMENTO_CHAVE);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITMO);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    public static boolean verificarSenha(String senhaDigitada, String saltArmazenado, String hashArmazenado) {
        String hashCalculado = gerarHashSenha(senhaDigitada, saltArmazenado);
        return hashCalculado.equals(hashArmazenado);
    }
}
