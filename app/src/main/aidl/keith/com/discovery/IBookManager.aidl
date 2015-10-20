package keith.com.discovery;
import keith.com.discovery.ipc.aidl.Book;
import keith.com.discovery.IOnNewBookArrivedListener;

/**
 * Created by Keith on 2015/10/11.
 */
//import keith.com.discovery.ipc.aidl.BookSerial;
interface IBookManager {
    List<Book> getBookList ();
    void addBook (in Book book);
    void registerListener (IOnNewBookArrivedListener listener);
    void unregisterListener (IOnNewBookArrivedListener listener);
}
