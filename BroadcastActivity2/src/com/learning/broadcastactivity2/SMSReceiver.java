package com.learning.broadcastactivity2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
	public SMSReceiver(){
		System.out.println("new BroadcastReceiver");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		System.out.println("Receive sms");
		
		Bundle bd = intent.getExtras();
		//there is a attribute pdus in bundle, and it is an array of object
		Object[] ObjectPDUS = (Object[])bd.get("pdus");
		//create a sms array
		SmsMessage[] message = new SmsMessage[ObjectPDUS.length];
		for(int i = 0; i < ObjectPDUS.length; i++){
			message[i] = SmsMessage.createFromPdu((byte[])ObjectPDUS[i]);
			System.out.println(message[i].getDisplayMessageBody());
		}
	}
}
