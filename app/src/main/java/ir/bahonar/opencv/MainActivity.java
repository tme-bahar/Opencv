package ir.bahonar.opencv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int res = R.drawable.env;
        Bitmap bm = BitmapFactory.decodeResource(getResources(), res);
        ImageView im = findViewById(R.id.imageView);
        final TextView tv = findViewById(R.id.textView);
        im.setImageResource(res);
        View v = findViewById(R.id.view);
        Button b = findViewById(R.id.button);
        im.setImageBitmap(Tools.getResizedBitmap(bm,60,75));
        
    }


}