package keith.com.discovery.ipc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import keith.com.discovery.constants.MyConstants;

/**
 * Created by Keith on 2015/10/15.
 * The Service point is service
 */
public class MessengerService extends Service{
    private static final String TAG = "MessengerService";
    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_CLIENT: {
                    System.out.println(msg);
                    Log.v("String!", msg.arg1 + "");
                    String result = msg.getData().getString("msg");
                    Log.v("String!@", result);
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null, MyConstants.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "Got it");
                    replyMessage.setData(bundle);
                    try {
                        client.send(replyMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default:
                    super.handleMessage(msg);
            }
        }
    }
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
