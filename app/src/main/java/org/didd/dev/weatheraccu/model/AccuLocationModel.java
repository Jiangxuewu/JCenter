package org.didd.dev.weatheraccu.model;

import org.didd.dev.weatheraccu.data.AccuLocationData;
import org.didd.http.IHttpCallback;

/**
 * Created by Jiangxuewu on 2017/12/5.
 */

public class AccuLocationModel extends AccuBaseModel {

    public AccuLocationModel(AccuLocationData data, IHttpCallback callback) {
        super(data, callback);
    }
}
