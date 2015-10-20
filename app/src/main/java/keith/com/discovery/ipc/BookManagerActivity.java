package keith.com.discovery.ipc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

import keith.com.discovery.IBookManager;
import keith.com.discovery.IOnNewBookArrivedListener;
import keith.com.discovery.R;
import keith.com.discovery.ipc.aidl.Book;
import keith.com.discovery.ipc.service.BookManagerService;

/**
 * Created by Keith on 2015/10/18.
 */
public class BookManagerActivity extends Activity {
    private static final String TAG = "BookManagerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManager;
    private Handler mHandler = new Handler () {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.i(TAG, "receive new Book:" + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, book).sendToTarget();
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                mRemoteBookManager = bookManager;
                List<Book> list = bookManager.getBookList();
                Log.i(TAG, "list type:" + list.getClass());
                Log.i(TAG, "query list:" + list.toString());

                Book newOne = new Book(3, "Both");
                bookManager.addBook(newOne);
                List<Book> newList = bookManager.getBookList();
                Log.i(TAG, "query list:" + newList.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.i(TAG, "unregister listener:" + mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
