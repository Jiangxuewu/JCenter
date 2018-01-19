package org.didd.scheme;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Jiangxuewu on 2018/1/18.
 * <p>help mul channel</p>
 * <p>call method {@link #getScheme(File, String)}</p>
 */
public class SchemeUtil {
    private static final int id = 0x71777777;
    private static final String CONTENT_CHARSET = "UTF-8";

    /**
     * get V2 extra info
     *
     * @param file apk file path
     * @param key  the key of return value
     * @return info
     */
    public static String getScheme(File file, String key) {
        Map<Integer, ByteBuffer> map = _getV2ExtroInfo(file);

        if (null == map) return null;

        ByteBuffer buf = map.get(id);

        if (null == buf) {
            return null;
        }

        try {
            return parseRes(new String(getBytes(buf), CONTENT_CHARSET), key);
        } catch (UnsupportedEncodingException e) {
            return parseRes(new String(getBytes(buf)), key);
        }
    }

    /**
     * get V2 extra info
     *
     * @param file apk file path
     * @return all info.
     */
    public static String getScheme(File file) {
        Map<Integer, ByteBuffer> map = _getV2ExtroInfo(file);

        if (null == map) return null;

        ByteBuffer buf = map.get(id);

        if (null == buf) {
            return null;
        }
        try {
            return parseRes(new String(getBytes(buf), CONTENT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            return parseRes(new String(getBytes(buf)));
        }
    }

    private static byte[] getBytes(final ByteBuffer byteBuffer) {
        final byte[] array = byteBuffer.array();
        final int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, arrayOffset + byteBuffer.position(),
                arrayOffset + byteBuffer.limit());
    }

    private static Map<Integer, ByteBuffer> _getV2ExtroInfo(File apkFile) {
        Map<Integer, ByteBuffer> idValues = null;
        try {
            RandomAccessFile randomAccessFile = null;
            FileChannel fileChannel = null;
            try {
                randomAccessFile = new RandomAccessFile(apkFile, "r");
                fileChannel = randomAccessFile.getChannel();
                final ByteBuffer apkSigningBlock2 = ApkUtil.findApkSigningBlock(fileChannel).getFirst();
                idValues = ApkUtil.findIdValues(apkSigningBlock2);
            } catch (IOException ignore) {
            } finally {
                try {
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                } catch (IOException ignore) {
                }
                try {
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException ignore) {
                }
            }
        } catch (SignatureNotFoundException ignore) {
        }

        return idValues;
    }

    private static String parseRes(String res, String key) {
        try {
            JSONObject json = new JSONObject(res);
            if (json.has(key)) {
                return json.getString(key);
            } else {
                return null;
            }
        } catch (JSONException e) {
            return null;
        }
    }

    private static String parseRes(String res) {
        return res;
    }
}
