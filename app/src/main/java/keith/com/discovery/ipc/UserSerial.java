package keith.com.discovery.ipc;

import java.io.Serializable;

/**
 * Created by Keith on 2015/10/7.
 */
public class UserSerial implements Serializable {
    public int userId;
    public String userName;
    public boolean isMale;
    public UserSerial (int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }
}
