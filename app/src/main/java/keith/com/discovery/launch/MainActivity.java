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
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import keith.com.discovery.R;
import keith.com.discovery.ipc.Book;
import keith.com.discovery.ipc.UserParcel;
import keith.com.discovery.ipc.UserManager;
import keith.com.discovery.ipc.UserSerial;


public class MainActivity extends Activity {
    private TextView mTextView;
    private Button mDo;
    private Button mUndo;

    public static final String PARCEL_KEY = "KEY";
    public static final String DIR_PATH = Environment.getExternalStorageDirectory() + "/test1.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManager.sUserId = 2;
        persisToFile();
        mTextView = (TextView) findViewById(R.id.text);
        mDo = (Button) findViewById(R.id.doSerializable);
        mUndo = (Button) findViewById(R.id.undoSerializable);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SecondActivity.class));
                UserParcel userParcel = new UserParcel(13, "Will", true, new Book("Discovery"));

                Intent intent = new Intent("action1");
                intent.putExtra(PARCEL_KEY, userParcel);
                intent.addCategory("c1");
                intent.setDataAndType(Uri.parse("file://abc"), "text/plain");
                startActivity(intent);
            }
        });

        mDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*UserSerial user = new UserSerial (0, "jake", true);
                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(
                            new FileOutputStream("cache.txt"));
                    out.writeObject(user);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }*/

                UserParcel userParcel = null;
                File cacheFile = new File(DIR_PATH);
                if (cacheFile.exists()) {
                    ObjectInputStream objectInputStream = null;

                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(DIR_PATH));
                        userParcel = (UserParcel) objectInputStream.readObject();
                        Toast.makeText(MainActivity.this, userParcel.userName, Toast.LENGTH_LONG).show();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        if (objectInputStream != null)
                            try {
                                objectInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }

                }

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
                UserParcel userParcel = new UserParcel(1, "Hello process", true,
                        new Book("Discovery"));
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File dir = Environment.getExternalStorageDirectory();
                    ObjectOutputStream objectOutputStream = null;
                    try {
                         objectOutputStream =
                                new ObjectOutputStream(new FileOutputStream(DIR_PATH));
                        objectOutputStream.writeObject(userParcel);
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
