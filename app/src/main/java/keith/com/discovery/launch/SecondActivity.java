package keith.com.discovery.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import keith.com.discovery.R;
import keith.com.discovery.ipc.UserManager;
import keith.com.discovery.ipc.UserParcel;


public class SecondActivity extends Activity {
    private TextView mTextView;
    private Button inParcel;
    private Button outParcel;
    private TextView mInfo;

    public static final String OBJECT_KEY = "OBJECT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextView = (TextView) findViewById(R.id.text2);
        mInfo = (TextView) findViewById(R.id.mInfo);
        mTextView.setText(String.valueOf(UserManager.sUserId));
        inParcel = (Button) findViewById(R.id.inParcel);
        outParcel = (Button) findViewById(R.id.outParcel);

        Bundle bundle = getIntent().getExtras();
        UserParcel userParcel =  bundle.getParcelable(MainActivity.PARCEL_KEY);

        mInfo.setText(userParcel.book.bookName);
       // mTextView.setText(userParcel.userName);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
