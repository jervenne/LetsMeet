package com.mobidev;

import com.mobidev.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SendInvitationActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to send_invitation.xml
        setContentView(R.layout.send_invitation);
        
       Button selectDateBtn = (Button) findViewById(R.id.sendButton);
        
        // Listening to Send Button
       selectDateBtn.setOnClickListener(new View.OnClickListener() {
    	   public void onClick(View v) {
    	    	Log.i("clicks","You clicked Send Invitation button");
    	        Intent i = new Intent(SendInvitationActivity.this, SummaryOfPollsActivity.class);
    	        startActivity(i);
    	    }
       });
    }
}
