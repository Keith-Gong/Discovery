package keith.com.discovery.launch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import keith.com.discovery.R;
import keith.com.discovery.ipc.BookManagerActivity;
import keith.com.discovery.ipc.BookSerial;
import keith.com.discovery.ipc.UserParcel;
import keith.com.discovery.ipc.UserManager;
import keith.com.discovery.ipc.UserSerial;
import keith.com.discovery.ipc.contentprovider.ProviderActivity;
import keith.com.discovery.view.DispatchActivity;
import keith.com.discovery.view.ViewActivity;


public class MainActivity extends Activity {
    private TextView mTextView;
    private Button mDo;
    private Button mUndo;
    private Button mNext;
    private Button mBook;
    private Button mProviderBtn;
    private Button mGoToView;
    private Button mGoToDispatch;

    public static final String PARCEL_KEY = "KEY";
    public static final String DIR_PATH = Environment.getExternalStorageDirectory() + "/test1.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(MainActivity.class.getClassLoader() + " ");
        UserManager.sUserId = 2;
        mTextView = (TextView) findViewById(R.id.text);
        mDo = (Button) findViewById(R.id.doSerializable);
        mUndo = (Button) findViewById(R.id.undoSerializable);
        mNext = (Button) findViewById(R.id.goToMessenger);
        mBook = (Button) findViewById(R.id.goToBM);
        mProviderBtn = (Button) findViewById(R.id.goToProvider);
        mGoToView = (Button) findViewById(R.id.goToView);
        mGoToDispatch = (Button) findViewById(R.id.goToDispatch);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MessengerActivity.class));
            }
        });
        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookManagerActivity.class));
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SecondActivity.class));
                UserParcel userParcel = new UserParcel(13, "Will", true, new BookSerial("Discovery"));

                Intent intent = new Intent("action1");
                intent.putExtra(PARCEL_KEY, userParcel);
                intent.addCategory("c1");
                intent.setDataAndType(Uri.parse("file://abc"), "text/plain");
                startActivity(intent);
            }
        });

        mProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProviderActivity.class));
            }
        });

        mDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persisToFile();
            }
        });

        mUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectInputStream in = null;
                UserSerial user = null;
                try {
                    in = new ObjectInputStream(new FileInputStream("cache.txt"));
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
                if (user != null)
                    mTextView.setText(user.userName);
            }
        });
        mGoToView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
            }
        });

        mGoToDispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DispatchActivity.class));
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

    private void persisToFile () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserSerial userSerial = new UserSerial(1, "Hello process", true);
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File dir = Environment.getExternalStorageDirectory();
                    ObjectOutputStream objectOutputStream = null;
                    try {
                         objectOutputStream =
                                new ObjectOutputStream(new FileOutputStream(DIR_PATH));
                        objectOutputStream.writeObject(userSerial);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (objectOutputStream != null)
                            try {
                                objectOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }
            }
        }).start();
    }
}
