package keith.com.discovery.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keith on 2015/10/8.
 */
public class UserParcel implements Parcelable {
    public int userId;
    public String userName;
    public boolean isMale;

    public BookSerial bookSerial;

    public UserParcel(int id, String name, boolean isMale, BookSerial bookSerial) {
        this.userId = id;
        this.userName = name;
        this.isMale = isMale;
        this.bookSerial = bookSerial;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale ? 1 : 0);
        out.writeParcelable(bookSerial, 0);
    }

    public static final Creator<UserParcel> CREATOR = new Creator<UserParcel>() {
        @Override
        public UserParcel createFromParcel(Parcel in) {
            return new UserParcel(in);
        }

        @Override
        public UserParcel[] newArray(int size) {
            return new UserParcel[size];
        }
    };

    private UserParcel (Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1;
        bookSerial = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }
}


