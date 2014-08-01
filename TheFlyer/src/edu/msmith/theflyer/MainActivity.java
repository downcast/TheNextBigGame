//sherdon brown
// Marcus Smith rules 

// Kratos 1
package edu.msmith.theflyer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		float num= 10;
		
		TextView text= (TextView) findViewById(R.id.text);
		text.setTranslationX(text.getTranslationX()+num);
		ViewPropertyAnimator vpa;
		//vpa.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

//Sherdon Brown
//game app flyer
