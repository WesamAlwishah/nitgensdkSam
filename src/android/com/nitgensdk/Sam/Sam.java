package com.nitgensdk;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class Sam extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void startTrip(String driverID, ArrayList<String> taskIDs, final CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();
        HTTransmitterService transmitterService = HTTransmitterService.getInstance(context);
        HTTripParams htTripParams = new    
        HTTripParamsBuilder().setDriverID(driverID)
                .setTaskIDs(taskIDs)
                .createHTTripParams();
        transmitterService.startTrip(htTripParams, new HTTripStatusCallback() {
            @Override
            public void onSuccess(boolean isOffline, HTTrip htTrip) {
                // call success callback
                callbackContext.success(result);
            }
            @Override
            public void onError(Exception e) {
                // call error callback
                callbackContext.error(result);
            }
        });
    }
}


