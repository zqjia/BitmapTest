package com.example.bmptest;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

	private ImageView imageView;
	private Button button;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView = (ImageView)this.findViewById(R.id.img);
        button = (Button)this.findViewById(R.id.button);
        
        this.button.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				imageView.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.weixin, 100, 75));
			}
        	
        });
    }


    private static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight)
    {
    	final BitmapFactory.Options options = new BitmapFactory.Options();
    	options.inJustDecodeBounds = true;
    	BitmapFactory.decodeResource(res, resId, options);
    	
    	options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
    	options.inJustDecodeBounds = false;
    	return BitmapFactory.decodeResource(res, resId, options);
    }
    
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
    	final int width = options.outWidth;
    	final int height = options.outHeight;
    	int inSampleSize = 1;
    	
    	if(height > reqHeight || width > reqWidth)
    	{
    		final int halfHeight = height / 2;
    		final int halfWidth= width/ 2;
    		
    		while((halfHeight / inSampleSize) > reqHeight
    				&& (halfWidth / inSampleSize) > reqWidth)
    		{
    			inSampleSize *= 2;
    		}
    		
    	}
    	return inSampleSize;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
