package keith.com.discovery.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import keith.com.discovery.R;
import keith.com.discovery.ipc.UserManager;
import keith.com.discovery.ipc.UserParcel;
import keith.com.discovery.ipc.UserSerial;


public class SecondActivity extends Activity {
    private TextView mTextView;
    private Button inParcel;
    private Button outParcel;
    private TextView mInfo;

    private String userName;

    public static final String OBJECT_KEY = "OBJECT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.v("String!", SecondActivity.class.getClassLoader() + "");
        System.out.println("!!!!" + SecondActivity.class.getClassLoader() + " ");
        System.out.println("SecondActivity");
        mTextView = (TextView) findViewById(R.id.text2);
        mInfo = (TextView) findViewById(R.id.mInfo);
        mTextView.setText(String.valueOf(UserManager.sUserId));
        inParcel = (Button) findViewById(R.id.inParcel);
        outParcel = (Button) findViewById(R.id.outParcel);

        Bundle bundle = getIntent().getExtras();
        UserParcel userParcel =  bundle.getParcelable(MainActivity.PARCEL_KEY);

        restoreFile();
        if (userName != null)

            mInfo.setText(userName);
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

    private void restoreFile () {
        ObjectInputStream in = null;
        UserSerial user = null;
        try {
            in = new ObjectInputStream(new FileInputStream(MainActivity.DIR_PATH));
            user = (UserSerial) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (user != null) {
            System.out.println(user.userName);
            userName = user.userName;
        }
    }
}
