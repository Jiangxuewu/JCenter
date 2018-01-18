package org.didd.common.log;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Log input
 * <p>
 * save log to sdcard if {@link #setIsWriteToFile(boolean)} params is true
 * </p>
 *
 * @author Jxw
 */
public class FLog {

    private static String LOG_FILE_DIR_NAME = "Logs";

    /**
     * The path of log in sdcard.
     */
    public static String LOG_FILE_DIR = FileUtils.getSaveFilePath() + LOG_FILE_DIR_NAME + File.separator;

    /**
     * The prefix of log file.
     */
    public static String LOG_FILE_PREFIX = "sky_log_";// e.g.->full name
    // :sky_log_0.txt
    /**
     * The suffix of log file.
     */
    public static String LOG_FILE_SUFFIX = ".txt";

    /**
     * The index of log file.
     */
    public static int index = 0;

    /**
     * The max count of log files.
     */
    private static int defaultMaxFiles = 40;
    /**
     * The max size of one log file.
     */
    private static int maxFiles = defaultMaxFiles;

    /**
     * Single thread pool
     */
    private static ExecutorService executorService = null;

    private static FileWriter fileWriter = null;

    /**
     * The max size of one log file is 500kb
     */
    private static int file_size = 1024 * 500;

    /**
     * Tag of whether write to log file
     */
    private static boolean isWriteToFile = false;

    /**
     * set Tag of whether write to log file.
     *
     * @param isWriteToFile isWriteToFile
     */
    public static void setIsWriteToFile(boolean isWriteToFile) {
        FLog.isWriteToFile = isWriteToFile;
    }

    public static void setDirName(String dirName) {
        LOG_FILE_DIR_NAME = dirName;
    }

    private static void init() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        initIndex();
    }

    private static void initIndex() {
        File dir = new File(LOG_FILE_DIR);
        long modify = System.currentTimeMillis();
        long fileModifyTime;
        long tmpTime;
        String path = "";
        if (null != dir && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (null == files) {
                return;
            }
            for (File file : files) {
                if (null != file && file.exists() && !file.isDirectory()) {
                    fileModifyTime = file.lastModified();
                    tmpTime = System.currentTimeMillis() - fileModifyTime;
                    if (tmpTime < modify) {
                        modify = tmpTime;
                        path = file.getName();
                    }
                }
            }
        }

        if (!TextUtils.isEmpty(path) && path.contains(LOG_FILE_PREFIX)) {

            boolean isNeedNewFile = new File(LOG_FILE_DIR + path).length() >= file_size;

            index = Integer.valueOf(path.replace(LOG_FILE_PREFIX, "").replace(
                    LOG_FILE_SUFFIX, ""));
            if (isNeedNewFile) {
                index++;
                try {
                    FileUtils.deleteFile(LOG_FILE_DIR + getFileName());
                    FileUtils.createFile(LOG_FILE_DIR + getFileName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * set the prefix of log file.
     *
     * @param prefix prefix
     */
    public static void setLogFilePrefix(String prefix) {
        if (!TextUtils.isEmpty(prefix) && !FLog.LOG_FILE_PREFIX.equals(prefix)) {
            index = 0;
        }
        FLog.LOG_FILE_PREFIX = prefix;
    }

    /**
     * set the suffix of log file.
     *
     * @param suffix e.g. ".txt"
     */
    public static void setLogFileSuffix(String suffix) {
        if (TextUtils.isEmpty(suffix)) {
            return;
        }
        if (!suffix.contains(".")) {
            suffix = "." + suffix;
        }
        if (!FLog.LOG_FILE_SUFFIX.equals(suffix)) {
            index = 0;
        }
        FLog.LOG_FILE_SUFFIX = suffix;
    }

    /**
     * set the max count of log files.
     *
     * @param maxFiles maxFiles
     */
    public static void setMaxFiles(int maxFiles) {
        FLog.maxFiles = maxFiles;
    }

    /**
     * get log file name.
     *
     * @return file name
     */
    private synchronized static String getFileName() {
        if (index >= maxFiles) {
            index = 0;
        }
        return LOG_FILE_PREFIX + index + LOG_FILE_SUFFIX;
    }

    private static void checkFileSize() {
        File file = new File(LOG_FILE_DIR + getFileName());

        if (file.length() >= file_size) {
            index++;
            FileUtils.deleteFile(LOG_FILE_DIR + getFileName());
        }

    }

    /**
     * @param tag
     * @param format
     */
    private static void write(String tag, String format) {
        if (!isWriteToFile) {
            return;
        }
        final String str = DateFormat.format("yyyy-MM-dd HH:mm:ss",
                System.currentTimeMillis()).toString()
                + "   " + tag + "   " + format + "\n";
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    checkFileSize();
                    FileUtils.createFile(LOG_FILE_DIR + getFileName());
                    fileWriter = new FileWriter(LOG_FILE_DIR + getFileName(),
                            true);
                    fileWriter.write(str, 0, str.length());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Low-level logging call.
     *
     * @param priority The priority/type of this log message
     * @param tag      Used to identify the source of a log message. It usually
     *                 identifies the class or activity where the log call occurs.
     * @param msg      The message you would like logged.
     * @return The number of bytes written.
     */
    private static void prient(int priority, String tag, String msg) {
        if (priority >= Log.DEBUG) {
            Log.println(priority, tag, msg);
        }
    }

    public static void d(String tag, String format) {
        if (null == executorService) {
            init();
        }
        write(tag, format);
        prient(Log.DEBUG, tag, format);
    }

    public static void e(String tag, String format) {
        if (null == executorService) {
            init();
        }
        write(tag, format);
        prient(Log.ERROR, tag, format);
    }

    public static void i(String tag, String format) {
        if (null == executorService) {
            init();
        }
        write(tag, format);
        prient(Log.INFO, tag, format);
    }

    public static void w(String tag, String format) {
        if (null == executorService) {
            init();
        }
        write(tag, format);
        prient(Log.WARN, tag, format);
    }
}
