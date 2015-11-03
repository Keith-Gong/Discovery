package keith.com.discovery.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.TextView;

import keith.com.discovery.R;

/**
 * Created by Keith on 11/1/15.
 */
public class ViewActivity extends Activity {
    private Scroller mScroller = new Scroller(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view);
        super.onCreate(savedInstanceState);

        final TextView textView = (TextView) findViewById(R.id.viewTextView) ;
        Button button = (Button) findViewById(R.id.viewButton);

        button.setOnClickListener(new View  .OnClickListener() {
            @Override
            public void onClick(View v) {
                //textView.scrollBy(150, 0);
            }
        });

        textView.computeScroll();

    }

    private void smoothScrollTo (int destX, int destY, View view) {
        int scrollX = view.getScrollX();
        int deltaX = destX - scrollX;
        mScroller.startScroll(scrollX, 0, deltaX, 0, 1000);
        view.invalidate();
    }
}
