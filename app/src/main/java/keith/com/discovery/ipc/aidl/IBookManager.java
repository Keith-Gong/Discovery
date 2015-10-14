package keith.com.discovery.ipc.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

import keith.com.discovery.ipc.Book;

/**
 * Created by Keith on 2015/10/11.
 */
public interface IBookManager extends IInterface {
    static final String DESCRIPTOR = "keith.com.discovery.ipc.aidl.IBookManager";
    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;
    public List<Book> getBookList() throws RemoteException;
    public void addBook(Book book) throws RemoteException;

    public class BookManagerImpl extends Binder implements IBookManager {
        public BookManagerImpl () {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IBookManager asInterface (IBinder obj) {
            if (obj == null)
                return null;
            IInterface inn = obj.queryLocalInterface(DESCRIPTOR);
            if (inn != null && (inn instanceof IBookManager))
                return (IBookManager) inn;
            return new Proxy(obj);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            //TODO
            return null;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            //TODO
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        private static class Proxy implements IBookManager {
            private IBinder mRemote;
            Proxy (IBinder remote) {
                mRemote = remote;
            }

            @Override
            public List<Book> getBookList() throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                List<Book> result;
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(TRANSACTION_getBookList, data, reply, 0);
                    reply.readException();
                    result = reply.createTypedArrayList(Book.CREATOR);
                } finally {
                    data.recycle();
                    reply.recycle();
                }
                return result;
            }

            @Override
            public void addBook(Book book) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();

                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    if (book != null) {
                        data.writeInt(1);
                        book.writeToParcel(data, 0);
                    } else {
                        data.writeInt(0);
                    }
                    mRemote.transact(TRANSACTION_addBook, data, reply, 0);
                    reply.readException();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            public String getInterfaceDescriptor () {
                return DESCRIPTOR;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString (DESCRIPTOR);
                    return true;
                }
                case  TRANSACTION_getBookList: {
                    data.enforceInterface(DESCRIPTOR);
                    List<Book> result = this.getBookList();
                    reply.writeNoException();
                    reply.writeTypedList(result);
                    return true;
                }
                case TRANSACTION_addBook: {
                    data.enforceInterface(DESCRIPTOR);
                    Book arg0;
                    if (0 != data.readInt()) {
                        arg0 = Book.CREATOR.createFromParcel(data);
                    } else {
                        arg0 = null;
                    }
                    this.addBook(arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
    }
}
