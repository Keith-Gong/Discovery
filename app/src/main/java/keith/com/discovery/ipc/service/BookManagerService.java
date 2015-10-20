package keith.com.discovery.ipc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import keith.com.discovery.IBookManager;
import keith.com.discovery.IOnNewBookArrivedListener;
import keith.com.discovery.ipc.aidl.Book;

/**
 * Created by Keith on 2015/10/18.
 */
public class BookManagerService extends Service {
    private static final String TAG = "BMS";
    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList =
            new CopyOnWriteArrayList<IOnNewBookArrivedListener>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (!mListenerList.contains(listener))
                mListenerList.add(listener);
            else
                Log.i(TAG, "Already exists");
            Log.i(TAG, "register size:" + mListenerList.size());
        }


        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (mListenerList.contains(listener)) {
                mListenerList.remove(listener);
                Log.i(TAG, "unregister succeed");
            }
            else
                Log.i(TAG, "not found, can not unregister");
            Log.i(TAG, "unregistered, current size:" + mListenerList.size());
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "iOS"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void onNewBookArrived (Book book) throws Exception {
        mBookList.add(book);
        Log.i(TAG, "onNewBookArrived, notify listener:" + mListenerList.size());
        for (int i = 0; i < mListenerList.size(); i++) {
            IOnNewBookArrivedListener listener = mListenerList.get(i);
            Log.i(TAG, "onNewBook Arrived, notify listener:" + listener);
            listener.onNewBookArrived(book);
        }
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book book = new Book(bookId, "new Book#" + bookId);
                try {
                    onNewBookArrived(book);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
