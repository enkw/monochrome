package com.example.androidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
        SampleView sv;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        sv = new SampleView(this);
        LinearLayout l = new LinearLayout(this);
        Button bt = new Button(this);
       // ImageButton ib =new ImageButton(this);	//imagebutton
        
        
        bt.setText("撮影");
        bt.setOnClickListener(new TakeButton());
        
      //  ib.setOnClickListener(new TakeButton());	//imagebutton
        
        
        l.addView(sv);
        setContentView(l);
        addContentView(bt, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }
    
    class TakeButton implements OnClickListener{

                @Override
                public void onClick(View v) {
                        sv.takePicture();
                }
            
    }

}