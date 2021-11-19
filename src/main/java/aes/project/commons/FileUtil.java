package aes.project.commons;

import org.apache.commons.io.IOUtils;

public class FileUtil {

    private static String SPANISH_CHARSET= "iso-8859-1";

    public static String getStringRequest(String fileName, String path) {
        String output = null;
        try {
             output = IOUtils.toString(FileUtil.class.getResourceAsStream(path + fileName), SPANISH_CHARSET);
        }catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }
}
