package org.didd.admob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/2.
 */

/*public*/ class DB {

    private static final String TAG = "DB";

    private static DB mInstance;
    private DaoSession mDaoSession;

    public static DB getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new DB();
            }
            return mInstance;
        }
    }

    public DB() {
    }

    public void init(Context context, String dbName) {
        DBHelper mHelper = new DBHelper(context, dbName, null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
