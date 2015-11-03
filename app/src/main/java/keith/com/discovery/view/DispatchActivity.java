package keith.com.discovery.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import keith.com.discovery.R;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Keith on 11/2/15.
 */
public class DispatchActivity extends Activity {
    public static final String TAG = "DISPATCH_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        LinearLayout father1 = (LinearLayout) findViewById(R.id.father1);
        LinearLayout father2 = (LinearLayout) findViewById(R.id.father2);
        LinearLayout father3 = (LinearLayout) findViewById(R.id.father3);
        Button btn = (Button) findViewById(R.id.btn);

        father1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "father1");
                Toast.makeText(DispatchActivity.this, "father1", Toast.LENGTH_SHORT).show();
            }
        });

        father2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "father2");
                Toast.makeText(DispatchActivity.this, "father2", Toast.LENGTH_SHORT).show();
            }
        });

        father3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "father3");
                Toast.makeText(DispatchActivity.this, "father3", Toast.LENGTH_SHORT).show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "btn");
                Toast.makeText(DispatchActivity.this, "btn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
