package keith.com.discovery;
import keith.com.discovery.ipc.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived (in Book book);
}