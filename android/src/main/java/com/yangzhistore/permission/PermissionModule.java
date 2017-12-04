package com.yangzhistore.permission;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.security.auth.login.LoginException;

/**
 * Created by yang on 17-11-30.
 */

public class PermissionModule extends ReactContextBaseJavaModule {
    public Context mContext;
    public PermissionModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext=reactContext;
    }

    @Override
    public String getName() {
        return "PermissionModule";
    }

    @ReactMethod
    public void checkCamera(Callback successCallback){
        Log.e("----->>>11", "checkCamera: ");
            successCallback.invoke(cameraIsCanUse());
    }
    public boolean cameraIsCanUse() {
        Log.e("----->>>33", "checkCamera: ");
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }
}
