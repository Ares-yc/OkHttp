package com.ares.utilslibrary;

import android.support.annotation.NonNull;

/**
 * File tools
 * Created by Yan Cui on 2017/2/28/028.
 */

public class FileUtil {

    public static String getFileName(@NonNull String filePath) {
        return filePath.lastIndexOf("/") == -1 ? "" : filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
    }
}
