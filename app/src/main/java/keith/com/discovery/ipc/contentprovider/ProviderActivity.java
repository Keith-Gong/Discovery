package keith.com.discovery.ipc.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import keith.com.discovery.R;
import keith.com.discovery.ipc.aidl.Book;

/**
 * Created by Keith on 2015/10/18.
 */
public class ProviderActivity extends Activity {
    private static final String TAG = "ProviderActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Uri bookUri = Uri.parse("content://keith.com.discovery.provider/book");
        Uri userUri = Uri.parse("content://keith.com.discovery.provider/user");
        /*getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);*/

        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", 6);
        contentValues.put("name", "TEST");
        getContentResolver().insert(bookUri, contentValues);

        Cursor bookCursor = getContentResolver().query(bookUri, new String[] {"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.id = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            Log.d(TAG, book.toString());
        }
    }
}
