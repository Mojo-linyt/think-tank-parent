package cn.linyt.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName Base64Util
 * @Description TODO    Base64加密解密工具类
 * @Author Mojo
 * @Date 2020/4/14 4:27
 * @Version 1.0
 **/
@Slf4j
public class Base64Util {

//    private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

    private static final String charset = "utf-8";

    /**
     * 解密
     * @param data
     * @return
     */
    public static String decode(String data) {
        try {
            if (null == data) {
                return null;
            }

            return new String(Base64.decodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            log.error(String.format("字符串：%s，解密异常", data), e);
        }

        return null;
    }

    /**
     * 加密
     * @param data
     * @return
     */
    public static String encode(String data) {
        try {
            if (null == data) {
                return null;
            }
            return new String(Base64.encodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            log.error(String.format("字符串：%s，加密异常", data), e);
        }

        return null;
    }
}
