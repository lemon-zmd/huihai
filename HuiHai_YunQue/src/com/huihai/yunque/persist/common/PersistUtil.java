package com.huihai.yunque.persist.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class PersistUtil {

    /**
     * the default persist directory name.
     */
    private static final String APP_DIR = "hui_hai";

    private static final String PERSIST_DB_NAME = "hui_hai.db";

    /**
     * this method can persist the the data to SQLite db. and the database is
     * existed in external sd card.
     */
    public static SQLiteDatabase getExternalDBHandler() {
        return SQLiteDatabase.openOrCreateDatabase(getDBFile(), null);
    }

    public static void initDB(SQLiteDatabase sdb, final String initStatements) {
        sdb.execSQL(initStatements);
    }

    /**
     * 
     * @param fileName
     * @return true if file is already there. false if a new file is created.
     */
    private static boolean checkAndCreateUnderExternalPath(String rootDir) {
        File f = new File(rootDir + "/" + APP_DIR + "/" + PERSIST_DB_NAME);
        boolean existed = f.exists();
        if (!existed) {
            try {
                // create the father dir.
                checkAndCreateDir(rootDir + "/" + APP_DIR);
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return existed;
    }

    private static String fullDBFileName() {
        if (canAccessSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + APP_DIR + "/" + PERSIST_DB_NAME;
        } else {
            return null;
        }
    }

    private static void copy(InputStream from, FileOutputStream to) throws IOException {
        byte[] buffer = new byte[7168];
        int count = 0;
        while ((count = from.read(buffer)) > 0) {
            to.write(buffer, 0, count);
        }
        from.close();
        to.close();
    }

    public static File getDBFile() {
        return new File(fullDBFileName());
    }

    private static void checkAndCreateDir(String fullURL) {
        File f = new File(fullURL);
        if (!f.exists()) {
            f.mkdir();
        }
    }

    /**
     * check if the SD card is accessed.
     * 
     * @return true only if the SD card is there and writable.
     */
    private static boolean canAccessSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * init DB if needed each time. Everything can be used afterward. copy the
     * existing db under assets/hui_hai.db to external resource.
     * 
     * @return true if successful false if fail
     */
    public static boolean initDB(Context context) {
        boolean operationStatus = false;
        if (canAccessSDCard()) {
            // copy the db under assets to externalstorageState
            String externalStorageDirPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!checkAndCreateUnderExternalPath(externalStorageDirPath)) {
                try {
                    InputStream db_stream = context.getAssets().open(PERSIST_DB_NAME);
                    FileOutputStream sd_db_stream = new FileOutputStream(fullDBFileName());
                    // copy them.
                    copy(db_stream, sd_db_stream);
                    operationStatus = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                operationStatus = true;
            }
        }
        return operationStatus;
    }
}
