package keith.com.discovery.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keith on 2015/10/9.
 */
public class BookSerial implements Parcelable {
    public String bookName;

    public BookSerial(String name) {
        this.bookName = name;
    }
    private BookSerial(Parcel in) {
        bookName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
    }

    public static Creator<BookSerial> CREATOR = new Creator<BookSerial>() {
        @Override
        public BookSerial createFromParcel(Parcel source) {
            return new BookSerial(source);
        }

        @Override
        public BookSerial[] newArray(int size) {
            return new BookSerial[size];
        }
    };
}
