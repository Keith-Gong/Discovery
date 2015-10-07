package keith.com.discovery.launch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import keith.com.discovery.R;
import keith.com.discovery.ipc.User;
import keith.com.discovery.ipc.UserManager;


public class MainActivity extends Activity {
    private TextView mTextView;
    private Button mDo;
    private Button mUndo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManager.sUserId = 2;
        mTextView = (TextView) findViewById(R.id.text);
        mDo = (Button) findViewById(R.id.doSerializable);
        mUndo = (Button) findViewById(R.id.undoSerializable);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SecondActivity.class));
                Intent intent = new Intent("action1");
                intent.addCategory("c1");
                intent.setDataAndType(Uri.parse("file://abc"), "text/plain");
                startActivity(intent);
            }
        });

        mDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User (0, "jake", true);
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
                }

            }
        });

        mUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectInputStream in = null;
                User user = null;
                try {
                    in = new ObjectInputStream(new FileInputStream("cache.txt"));
                    user = (User) in.readObject();
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
}
