package org.didd.version;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.transsnet.version.BuildConfig;

import org.didd.common.log.L;


/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/2.
 */

/*public*/ class DBHelper extends DaoMaster.OpenHelper {

    private static final String TAG = "DBHelper";

    public DBHelper(Context context, String name) {
        super(context, name);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (BuildConfig.DEBUG)
            L.i(TAG, "onUpgrade, oldVersion = " + oldVersion + ", newVersion = " + newVersion);
    }
}
