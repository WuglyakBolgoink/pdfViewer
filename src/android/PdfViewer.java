package de.neobaum.phonegap.plugin;

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

        String url = data.getString(0);

        if (action.equals("openPDF")) {
        	openPDF(url);
        }

        callbackContext.success();
        return true;
    }//execute

    private void openPDF(final String fileName) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                File file = new File(fileName);

                if (file.exists()) {

                    try {
                        Intent intent = new Intent();
                        //intent.setPackage("com.adobe.reader");
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                        cordova.getActivity().startActivity(intent);
                    } catch (android.content.ActivityNotFoundException e) {
                        Log.e(TAG, "PdfViewer: Error loding url " + fileName + ":" + e.toString());
                    }

                } else {
                    Log.e(TAG, "File not found: " + fileName);
                }
            }//run
        });//runOnUiThread
    }//openPDF
}
