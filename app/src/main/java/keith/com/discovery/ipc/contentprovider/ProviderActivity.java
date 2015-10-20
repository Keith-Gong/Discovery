package keith.com.discovery.ipc.contentprovider;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import keith.com.discovery.R;

/**
 * Created by Keith on 2015/10/18.
 */
public class ProviderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Uri uri = Uri.parse("content://keith.com.discovery.provider");
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
    }
}
