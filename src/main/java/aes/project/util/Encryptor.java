package aes.project.util;

import aes.project.commons.Constants;
import aes.project.commons.FileUtil;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
public class Encryptor {

    public static void main(String[] args) throws Exception {
        String data = FileUtil.getStringRequest("encrypt.txt", "/");
        log.info(":::::::::: ENCRYPT OPERATION START ::::::::::");
        log.info("DATA: {}", encrypt(Constants.AES_PASSWORD, Constants.AES_SALT, data));
        log.info(":::::::::: ENCRYPT OPERATION FINISH ::::::::::");
    }

    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(Constants.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(Constants.UTF_8), Constants.AES);
            Cipher cipher = Cipher.getInstance(Constants.TRANSFORMATION_INSTANCE);
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception var7) {
            log.error(var7.toString(), var7);
            return null;
        }
    }
}
