package aes.project.util;

import aes.project.commons.Constants;
import aes.project.commons.FileUtil;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
public class Decryptor {

    public static void main(String[] args) {
        String data = FileUtil.getStringRequest("decrypt.txt", "/");
        log.info(":::::::::: DECRYPT OPERATION START ::::::::::");
        log.info("DATA: {}", decrypt(Constants.AES_PASSWORD, Constants.AES_SALT, data));
        log.info(":::::::::: DECRYPT OPERATION FINISH ::::::::::");
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception var7) {
            log.error(var7.toString(), var7);
            return null;
        }
    }

}
