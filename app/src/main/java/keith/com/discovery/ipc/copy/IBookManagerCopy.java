/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\Study\\AndroidStudyProject\\Discovery\\app\\src\\main\\aidl\\keith\\com\\discovery\\IBookManager.aidl
 *//*

package keith.com.discovery.ipc;
*/
/**
 * Created by Keith on 2015/10/11.
 *//*
//import keith.com.discovery.ipc.aidl.BookSerial;

public interface IBookManagerCopy extends android.os.IInterface
{
    */
/** Local-side IPC implementation stub class. *//*

    public static abstract class Stub extends android.os.Binder implements keith.com.discovery.IBookManager
    {
        private static final java.lang.String DESCRIPTOR = "keith.com.discovery.IBookManagerCopy";
        */
/** Construct the stub at attach it to the interface. *//*

        public Stub()
        {
            this.attachInterface(this, DESCRIPTOR);
        }
        */
/**
         * Cast an IBinder object into an keith.com.discovery.IBookManagerCopy interface,
         * generating a proxy if needed.
         *//*

        public static keith.com.discovery.IBookManager asInterface(android.os.IBinder obj)
        {
            if ((obj==null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin!=null)&&(iin instanceof keith.com.discovery.IBookManager))) {
                return ((keith.com.discovery.IBookManager)iin);
            }
            return new IBookManagerCopy.Stub.Proxy(obj);
        }
        @Override public android.os.IBinder asBinder()
        {
            return this;
        }
        @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
        {
            switch (code)
            {
                case INTERFACE_TRANSACTION:
                {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_getValue:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _result = this.getValue();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_getList:
                {
                    data.enforceInterface(DESCRIPTOR);
                    java.util.List _result = this.getList();
                    reply.writeNoException();
                    reply.writeList(_result);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
        private static class Proxy implements keith.com.discovery.IBookManager
        {
            private android.os.IBinder mRemote;
            Proxy(android.os.IBinder remote)
            {
                mRemote = remote;
            }
            @Override public android.os.IBinder asBinder()
            {
                return mRemote;
            }
            public java.lang.String getInterfaceDescriptor()
            {
                return DESCRIPTOR;
            }
            */
/*List<Integer> getBookList ();
                void addNumber (in Integer bookSerial);*//*

            @Override public int getValue() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(IBookManagerCopy.Stub.TRANSACTION_getValue, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            @Override public java.util.List getList() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(IBookManagerCopy.Stub.TRANSACTION_getList, _data, _reply, 0);
                    _reply.readException();
                    java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
                    _result = _reply.readArrayList(cl);
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }
        static final int TRANSACTION_getValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }
    */
/*List<Integer> getBookList ();
        void addNumber (in Integer bookSerial);*//*

    public int getValue() throws android.os.RemoteException;
    public java.util.List getList() throws android.os.RemoteException;
}
*/
