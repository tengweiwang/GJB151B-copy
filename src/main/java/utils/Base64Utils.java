package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

/**
 * Created by ddgdd on 2018/9/7 0007 14:40
 */
public class Base64Utils {

    public static boolean Base64ToImg(String imgStr, String imgFilePath) {
        if (StringUtils.isEmpty(imgStr)) {  //图像数据为空
            return false;
        }

//        byte[] imgByte = Base64.decodeBase64(imgStr);
//        for(int i = 0; i < imgByte.length; i++) {
//            if(imgByte[i] < 0) {  //调整异常数据
//                if(imgByte[i] < 0) {
//                    imgByte[i] += 256;
//                }
//            }
//        }

        byte[] imgByte = DatatypeConverter.parseBase64Binary(imgStr);
        try {
            OutputStream outputStream = new FileOutputStream(imgFilePath);
            outputStream.write(imgByte);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getImgStr(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = new StringBuilder("data:image/png;base64,");
        result.append(new String(Base64.encodeBase64(data)));

        return result.toString();
    }

}
