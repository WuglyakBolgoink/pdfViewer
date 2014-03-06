package de.cyberkatze.phonegap.plugin;

import java.io.File;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class PdfViewer extends CordovaPlugin {

    private static final String TAG = "PdfViewerPlugin";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

    	Log.i(TAG, "EXECUTE PdfViewerPlugin");
    	Log.i(TAG, action);

    	Log.i(TAG, "getExternalStorageDirectory" + Environment.getExternalStorageDirectory().getAbsolutePath());


        String url = data.getString(0);


        if (action.equals("openPDF")) {
        	openPDF(Environment.getExternalStorageDirectory().getAbsolutePath() + url);
        }

        callbackContext.success();
        return true;
    }



    private void openPDF(final String fileName) {
    	Log.d(TAG, "EXECUTE PdfViewerPlugin openPDF-function");
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {


            	 File file = new File(fileName);
                 Log.i("PdfViewer", "check file exists? = " + file.exists());
                 if (file.exists()) {
                     try {
                          Intent intent = new Intent();
                          intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                         cordova.getActivity().startActivity(intent);

                         Log.i("PdfViewerr", "read: " + fileName);
                         //return true;
                     } catch (android.content.ActivityNotFoundException e) {
                         System.out.println("PdfViewer: Error loding url "+fileName+":"+ e.toString());
                         Log.i("PdfViewer", "error: " + fileName);
                         //return e.toString();
                     }

                 }else{
                     Log.i("PdfViewer", "notfound: " + fileName);
                     //return "file not found";
                 }


            }
        });
    }


}
