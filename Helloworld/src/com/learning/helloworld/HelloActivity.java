package com.learning.helloworld;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*1. start an activity, an activity is a class and it inherits from Activity
 *2. need to override on create function
 *3. each activity need to be registered in the manifest file
 *4. add necessary controls*/
public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        TextView myTestView = (TextView)findViewById(R.id.textView1);
        Button myButton = (Button)findViewById(R.id.button1);
        myTestView.setText("my first text view");
        myButton.setText("my first button");
        myButton.setOnClickListener(new myButtonListener());
    }

    class myButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			/*Intent intent = new Intent();
			intent.putExtra("testintent", "1234");
			intent.setClass(HelloActivity.this, OtherActivity.class);
			HelloActivity.this.startActivity(intent);
			*/
			Uri uri = Uri.parse("smsto://08000000123");
			Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", "SMS message");
			startActivity(intent);
		}

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hello, menu);
        return true;
    }
    
}
