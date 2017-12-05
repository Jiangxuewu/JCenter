package org.didd.dev.weather.httpx;

import android.text.TextUtils;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Jiangxuewu on 2017/12/4.
 */

public class CodeX {
    public static String encode(String str) {
        String str1 = Base64.encodeToString(k(str), 0);
        String str2 = str1.substring(0, 10) + a(5) + str1.substring(10);
        return f((a(5) + str2 + a(5)).replace("\n", ""));
    }

    public static String decode(String str) {
        if (TextUtils.isEmpty(str)) return null;
        String str1 = str.substring(0, -5 + str.length()).substring(5);
        String str2 = g(a(Base64.decode(str1.substring(0, 10) + str1.substring(15), 0)));
        return str2;

    }

    public static String g(String input) {
        if (TextUtils.isEmpty(input)) return input;
        try {
            return URLDecoder.decode(input, "utf-8");
        } catch (Exception localException) {
        }
        return input;
    }

    public static String f(String paramString) {
        if (paramString != null) ;
        try {
            String str = URLEncoder.encode(paramString, "utf-8");
            paramString = str;
            return paramString;
        } catch (Exception localException) {
        }
        return paramString;
    }


    private static String a(byte[] bytes) {
        if ((bytes == null) || (bytes.length == 0)) {
            return "";
        }
        StringBuffer resultSb = new StringBuffer();
        try {
            if (!b(bytes)) {
                return new String(bytes);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bytes)), "UTF-8"));
            while (true) {
                String str1 = bufferedReader.readLine();
                if (str1 == null)
                    break;
                resultSb.append(str1);
            }
        } catch (Exception localException) {
            return "";
        }
        return resultSb.toString();
    }

    private static boolean b(byte[] paramArrayOfByte) {
        return (paramArrayOfByte[0] == 31) && (paramArrayOfByte[1] == -117);
    }


    private static String a(int paramInt) {
        SecureRandom localSecureRandom = new SecureRandom();
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramInt; i++)
            localStringBuffer.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(localSecureRandom.nextInt("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length())));
        return localStringBuffer.toString();
    }

    private static byte[] k(String paramString) {
        try {
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramString.length());
            GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
            localGZIPOutputStream.write(paramString.getBytes("UTF-8"));
            localGZIPOutputStream.close();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            localByteArrayOutputStream.close();
            return arrayOfByte;
        } catch (Exception localException) {
        }
        return null;
    }
}
