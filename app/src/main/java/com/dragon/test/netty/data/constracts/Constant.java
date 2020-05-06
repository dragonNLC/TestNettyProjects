package com.dragon.test.netty.data.constracts;

import android.os.Environment;

import java.io.File;

/**
 * Created by lb on 2019/1/23.
 */

public class Constant {

    public static final int DEFAULT_NET_CONNECT_TIMEOUT = 3 * 1000;

    public static final int DEFAULT_SLEEP_TIME = 500;

    public static final String ROBOT_BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static final String APP_BASE_PATH = "AppleTimes";

    public static final String COMPANY_BASE_PATH = "GSProject";

    public static final String DATA_FOLDER = "data";

    public static final String CACHE_FOLDER = "cache";

    public static final String HEAD_CACHE_FOLDER = "headCache";

    public static final String WEL_CACHE_FOLDER = "welCache";

    public static final String TEMP_FOLDER = "temp";

    public static final String FACE_DB_FOLDER = "faceDB";

    public static final String USE_DB_FOLDER = "userDB";
    public static final String RECORD_DB_JSON_FILE = "record.json";

    public static final String OUTPUT_EXCEL_FILE_NAME = "日志.xls";

    public static final String APP_PATH = ROBOT_BASE_PATH + File.separator + APP_BASE_PATH + File.separator + COMPANY_BASE_PATH;

    public static final String DATA_PATH = APP_PATH + File.separator + DATA_FOLDER;

    public static final String TEMP_PATH = DATA_PATH + File.separator + TEMP_FOLDER;

    public static final String USB_FACE_FILE_PATH = TEMP_PATH + File.separator + "USBFace";

    public static final String USB_FACE_FOLDER_PATH = "员工人脸";

    public static final int SHOW_DELAY_TIME_MIN = 500;

    public static final int MAX_THREAD_POOL_SIZE = 10;

    public static final int FAIL_REPLY_SIZE = 3;

    //最大临时头像缓存数
    public static final int MAX_SAVE_TEMP_HEAD_COUNT = 10000;

}
