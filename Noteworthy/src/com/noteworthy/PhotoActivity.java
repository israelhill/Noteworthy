package com.noteworthy;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import com.github.kevinsawicki.http.*;

public class PhotoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		dispatchTakePictureIntent();//must be put in with a specific method, will repeat in oncreate
	}
	
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, 1);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bitmap bp = (Bitmap) data.getExtras().get("data");
		
		postToServer(BitmapToString(bp));
	}
	
	private String BitmapToString(Bitmap img)
	{
		ByteArrayOutputStream baos=new  ByteArrayOutputStream();
		img.compress(Bitmap.CompressFormat.PNG,50, baos);
	    byte [] b=baos.toByteArray();
	    String temp=Base64.encodeToString(b, Base64.DEFAULT);
	    return temp;
	}
	
	private void postToServer(final String input)
	{
		Thread thread = new Thread(new Runnable(){
		  @Override
		  public void run(){
			  int response = HttpRequest.post("http://noteworthy.cloudapp.net").send(input).code();
		  }
		});
		thread.start();
	}
	
}