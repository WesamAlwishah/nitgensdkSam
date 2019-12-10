package cordova.plugin.nitgen.sam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;
import com.nitgen.sam.R;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import com.nitgen.SDK.AndroidBSP.Android_Demo;
 
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
/**
 * This class echoes a string called from JavaScript.
 */
public class Sam extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("add")) { 
            this.add(args, callbackContext);
            return true;
        } else if(action.equals("substract"))
        {
        this.substract(args , callbackContext );
        }
        return false;
    }

    private void add(JSONArray args, CallbackContext callbackContext) {
        if (args != null  ) {
            try{
                int p1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                int p2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));
                callbackContext.success(""+ (p1+p2));

            }catch(Exception ex)
            {
                callbackContext.error("some thing error" + ex);

            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

       private void substract(JSONArray args, CallbackContext callbackContext) {
        Context context = cordova.getActivity().getApplicationContext();

        Intent intent = new Intent(context, Android_Demo.class);
        intent.putExtra("token", args.getString(0));
        intent.putExtra("host", args.getString(1));
        intent.putExtra("displayName", args.getString(2));
        intent.putExtra("resourceId", args.getString(3));
        intent.putExtra("hideConfig", true);
        intent.putExtra("autoJoin", true);

        this.cordova.getActivity().startActivity(intent);
    }
    
    // private void startTrip(String driverID, ArrayList<String> taskIDs, final CallbackContext callbackContext) {
    //     Context context = this.cordova.getActivity().getApplicationContext();
    //     HTTransmitterService transmitterService = HTTransmitterService.getInstance(context);
    //     HTTripParams htTripParams = new    
    //     HTTripParamsBuilder().setDriverID(driverID)
    //             .setTaskIDs(taskIDs)
    //             .createHTTripParams();
    //     transmitterService.startTrip(htTripParams, new HTTripStatusCallback() {
    //         @Override
    //         public void onSuccess(boolean isOffline, HTTrip htTrip) {
    //             // call success callback
    //             callbackContext.success(result);
    //         }
    //         @Override
    //         public void onError(Exception e) {
    //             // call error callback
    //             callbackContext.error(result);
    //         }
    //     });
    // }
}


