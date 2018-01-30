package org.didd.admob;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.didd.common.log.L;
import org.didd.http.BaseModel;
import org.didd.http.HttpApi;
import org.didd.http.HttpResponse;
import org.didd.http.HttpResponseBody;
import org.didd.http.IHttpCallback;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/13.
 * <p>manager for ad.</p>
 */

/*public*/ class AdManager implements IHttpCallback {

    private static final Object LOCK = new Object();
    private static AdManager mInstance;

    private boolean inited = false;

    private AdManager() {
    }

    private void save(AdIdBean bean) {
        if (null == bean) {
            return;
        }
        List<IDBean> list = bean.getData();
        if (null == list || list.isEmpty()) {
            return;
        }

        IDBeanDao dao = getDao();
        if (null == dao) {
            return;
        }
        dao.deleteAll();
        dao.insertInTx(list);
    }

    public String getAppID(boolean isFirst, String defValue) {
        if (isFirst) {
            return defValue;
        }

        IDBeanDao dao = getDao();
        if (null == dao) {
            return defValue;
        }
        List<IDBean> list = dao.queryBuilder().list();
        if (null != list && !list.isEmpty()) {
            for (IDBean item : list) {
                if (!TextUtils.isEmpty(item.getAppId())) {
                    return item.getAppId();
                }
            }
            return "";
        }
        return defValue;
    }

    public String getAdUnitID(String positionSerial, boolean isFirstStartApp, String defValue) {
        if (isFirstStartApp)
            return defValue;

        if (!inited) {
            return defValue;
        }
        IDBeanDao dao = getDao();
        if (null == dao) {
            L.u(getClass().getSimpleName(), "getAdUnitID, dao is null");
            return defValue;
        }
        List<IDBean> list = dao.queryBuilder().where(IDBeanDao.Properties.LocationId.eq(positionSerial)).limit(1).list();
        if (null != list && !list.isEmpty()) {
            return list.get(0).getAdId();
        }
        L.u(getClass().getSimpleName(), "getAdUnitID, defValue is " + defValue);
        return defValue;
    }

    private IDBeanDao getDao() {
        DaoSession session = DB.getInstance().getDaoSession();
        if (null == session) {
            return null;
        }
        return session.getIDBeanDao();
    }

    private AdIdBean parse(HttpResponse httpResponse) {
        if (null != httpResponse && httpResponse.code == 200) {
            HttpResponseBody body = httpResponse.getBody();
            if (null != body) {
                String json = body.getString();
                if (!TextUtils.isEmpty(json)) {
                    return new Gson().fromJson(json, AdIdBean.class);
                }
            }
        }
        return null;
    }

    public static AdManager getInstance() {
        synchronized (LOCK) {

            if (null == mInstance) {
                mInstance = new AdManager();
            }
            return mInstance;
        }
    }


    public void getAdList(String pkg, String channel) {
        getAdList(pkg, channel, this);
    }

    private void getAdList(String pkg, String channel, IHttpCallback listener) {
        if (BuildConfig.DEBUG) {
            L.d("", "pkg = " + pkg + ", channel = " + channel);
        }
        AdList adList = new AdList();

        ReqHead head = new ReqHead();
        ReqBodyAd body = new ReqBodyAd();

        body.packageName = pkg;
        body.channel = channel;

        head.sign = md5Of32("channel" + body.channel + "&packageName" + body.packageName);

        adList.reqHead = head;
        adList.reqBody = body;

        BaseModel model = new AdModel(adList, listener);

        HttpApi.getInstance().request(model);
    }

    private String md5Of32(String plainText) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                int i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void init(boolean init) {
        inited = init;
    }

    public boolean isInit() {
        return inited;
    }

    @Override
    public void result(HttpResponse httpResponse) {

        AdIdBean bean = parse(httpResponse);
        if (null == bean || bean.getCode() != 0) {
            return;
        }
        save(bean);
    }
}
