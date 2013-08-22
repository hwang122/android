package com.learning.multiply;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity01 extends Activity {
	private EditText factorone;
	private EditText factortwo;
	private TextView multiply;
	private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity01);
        factorone = (EditText)findViewById(R.id.factor1);
        factortwo = (EditText)findViewById(R.id.factor2);
        multiply = (TextView)findViewById(R.id.multiply);
        button = (Button)findViewById(R.id.button);
        
        multiply.setText(R.string.multiply);
        button.setOnClickListener(new calculatorListener());
    }
    
    class calculatorListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String factoroneStr = factorone.getText().toString();
			String factortwoStr = factortwo.getText().toString();
			
			Intent intent = new Intent();
			
			intent.putExtra("one", factoroneStr);
			intent.putExtra("two", factortwoStr);
			
			intent.setClass(Activity01.this, result.class);
			Activity01.this.startActivity(intent);
		}	
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	if(item.getItemId() == 1){
    		finish();
    	}
		return super.onOptionsItemSelected(item);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity01, menu);
        menu.add(0, 1, 1, R.string.out);
        menu.add(0, 2, 2, R.string.about);
        return true;
    }
    
}
