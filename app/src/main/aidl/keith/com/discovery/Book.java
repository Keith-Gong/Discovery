package keith.com.discovery;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keith on 2015/10/9.
 */
public class Book implements Parcelable {
    public String bookName;

    public Book(String name) {
        this.bookName = name;
    }
    private Book(Parcel in) {
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

    public static Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
