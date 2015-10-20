package keith.com.discovery.ipc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

/**
 * Created by Keith on 2015/10/18.
 */
public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider";
    private static final String AUTHORITY = "keith.com.discovery.provider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "book", USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        Log.i(TAG, "provider on create:" + Thread.currentThread().getName());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i(TAG, "provider on query:" + Thread.currentThread().getName());
        return null;
    }

    @Override
    public String getType(Uri uri) {
        Log.i(TAG, "provider on getType()");
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(TAG, "provider on insert");
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG, "provider on delete");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.i(TAG, "provider on update");
        return 0;
    }
}
