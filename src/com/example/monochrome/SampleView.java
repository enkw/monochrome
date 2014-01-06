package com.enokawa.monochrome;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

public class SampleView extends SurfaceView implements Callback, PictureCallback {

	private Camera camera;
	static private Context cont;

	public SampleView(Context context) {
		super(context);
		cont = context;
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera = Camera.open();
			camera.setPreviewDisplay(holder);
		} catch(IOException e) {
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int f, int w, int h) {
		Camera.Parameters p = camera.getParameters();
		p.setColorEffect(Camera.Parameters.EFFECT_MONO);
		p.setPreviewSize(w,h);
		camera.setParameters(p);
		camera.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
	}

	@Override
	public void onPictureTaken(byte[] data, Camera c) {
		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, null);
		MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bmp, "", null);
		Toast.makeText(cont, "保存しました。", Toast.LENGTH_SHORT).show();
		camera.startPreview();
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		if(me.getAction()==MotionEvent.ACTION_DOWN) {
			camera.takePicture(null,null,this);
		}
		return true;
	}
	
}
