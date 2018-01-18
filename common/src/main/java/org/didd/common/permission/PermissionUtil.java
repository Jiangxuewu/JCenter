package org.didd.common.permission;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangxuewu on 2016/1/22.
 * <p>Usage method:</p>
 * <p>Invocation method after getting an instance{@link #requestPermission(Activity, String[], OnCheckPermissionCallback)}Start application permissions</p>
 * <p>And  call in the Method{@link Activity#onRequestPermissionsResult(int, String[], int[])} , {@link #onRequestPermissionsResult(Activity, int, String[], int[])}</p>
 * <p>The result of the permission application is in the parameter{@link OnCheckPermissionCallback} In the callback</p>
 */
public class PermissionUtil {

    private OnCheckPermissionCallback mCheckPermissionCallback;
    private static final int REQUEST_CODE_CHECK_PERMISSION = 9;//Can only use lower 8 bits for requestCode

    private void test() {
//        String read = "";
//        read = Manifest.permission.READ_CALENDAR;//android.permission.READ_CALENDAR
//        read = Manifest.permission.WRITE_CALENDAR;//android.permission.WRITE_CALENDAR
//        read = Manifest.permission.CAMERA;//android.permission.CAMERA
//        read = Manifest.permission.READ_CONTACTS;//android.permission.READ_CONTACTS
//        read = Manifest.permission.WRITE_CONTACTS;//android.permission.WRITE_CONTACTS
//        read = Manifest.permission.GET_ACCOUNTS;//android.permission.GET_ACCOUNTS
//        read = Manifest.permission.ACCESS_FINE_LOCATION;//android.permission.ACCESS_FINE_LOCATION
//        read = Manifest.permission.ACCESS_COARSE_LOCATION;//android.permission.ACCESS_COARSE_LOCATION
//        read = Manifest.permission.RECORD_AUDIO;//android.permission.RECORD_AUDIO
//        read = Manifest.permission.READ_PHONE_STATE;//android.permission.READ_PHONE_STATE
//        read = Manifest.permission.CALL_PHONE;//android.permission.CALL_PHONE
//        read = Manifest.permission.READ_CALL_LOG;//android.permission.READ_CALL_LOG
//        read = Manifest.permission.WRITE_CALL_LOG;//android.permission.WRITE_CALL_LOG
//        read = Manifest.permission.ADD_VOICEMAIL;//com.android.voicemail.permission.ADD_VOICEMAIL
//        read = Manifest.permission.USE_SIP;//android.permission.USE_SIP
//        read = Manifest.permission.PROCESS_OUTGOING_CALLS;//android.permission.PROCESS_OUTGOING_CALLS
//        read = Manifest.permission.BODY_SENSORS;//android.permission.BODY_SENSORS
//        read = Manifest.permission.SEND_SMS;//android.permission.SEND_SMS
//        read = Manifest.permission.RECEIVE_SMS;//android.permission.RECEIVE_SMS
//        read = Manifest.permission.READ_SMS;//android.permission.READ_SMS
//        read = Manifest.permission.RECEIVE_WAP_PUSH;//android.permission.RECEIVE_WAP_PUSH
//        read = Manifest.permission.RECEIVE_SMS;//android.permission.RECEIVE_SMS
//        read = Manifest.permission.READ_EXTERNAL_STORAGE;//android.permission.READ_EXTERNAL_STORAGE
//        read = Manifest.permission.WRITE_EXTERNAL_STORAGE;//android.permission.WRITE_EXTERNAL_STORAGE
    }


    /**
     * Apply multiple rights at the same time
     * <p>Call in Method {@link Activity#onRequestPermissionsResult(int, String[], int[])}, Call Method {@link #onRequestPermissionsResult(Activity, int, String[], int[])} Dynamic application to implement API 23 permissions</p>
     *
     * @param context    activity
     * @param permission permission
     * @param callback   callback
     */
    public void requestPermission(Activity context, String[] permission, OnCheckPermissionCallback callback) {
        mCheckPermissionCallback = callback;
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionNeedApply = new ArrayList<String>();
            for (String item : permission) {
                int res = context.checkSelfPermission(item);
                if (res != PackageManager.PERMISSION_GRANTED) {//?????,???????.
                    permissionNeedApply.add(item);
                }
            }
            if (permissionNeedApply.size() > 0) {
                String[] lastPermission = new String[permissionNeedApply.size()];
                int i = 0;
                for (String item : permissionNeedApply) {
                    lastPermission[i++] = item;
                }
                context.requestPermissions(/*context, */lastPermission, REQUEST_CODE_CHECK_PERMISSION);
            } else {
                callback.requestPermissionSuccess();
            }
        } else {
            callback.requestPermissionSuccess();
        }
    }

    /**
     * Processing permission application callback
     * <p>In Activity Method {@link Activity#onRequestPermissionsResult(int, String[], int[])} The method calls the method in the method call method</p>
     *
     * @param activity     activity
     * @param requestCode  request code
     * @param permissions  permissions
     * @param grantResults permissions grant result
     */
    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CHECK_PERMISSION:
                if (null != mCheckPermissionCallback) {
                    if (verifyPermissions(grantResults)) {
                        mCheckPermissionCallback.requestPermissionSuccess();
                    } else {
//                        boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions.length > 0 ? permissions[0] : "");
                        boolean isTip = false;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            isTip = activity.shouldShowRequestPermissionRationale(/*activity,*/ permissions.length > 0 ? permissions[0] : "");
                        }
                        mCheckPermissionCallback.requestPermissionFailed(isTip, permissions.length > 0 ? permissions[0] : "");
                    }
                }
                break;
        }
    }

    private boolean verifyPermissions(int... grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public interface OnCheckPermissionCallback {
        void requestPermissionSuccess();

        void requestPermissionFailed(boolean isTip, String permission);
    }

}
