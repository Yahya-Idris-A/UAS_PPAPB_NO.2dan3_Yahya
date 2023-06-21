package com.example.uas_ppapb_no23_yahya;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();
    ObjectAnimator mAnimatorFlip;
    ObjectAnimator mAnimatorFadeIn;
    ObjectAnimator mAnimatorFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));
        mAnimatorFlip = ObjectAnimator.ofFloat(mImgView,"rotationY", 180);
        mAnimatorFadeIn = ObjectAnimator.ofFloat(mImgView, "alpha", 0, 1f);
        mAnimatorFadeOut = ObjectAnimator.ofFloat(mImgView, "alpha", 1f, 0);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();
        float centerX = vWidth / 2f;
        float centerY = vHeight / 2f;
        float radiusX = vWidth / 3f;
        float radiusY = vHeight / 4f;

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead(centerX, centerY, radiusX, radiusY);
        drawRightEye(vWidth, vHeight);
        drawLeftEye(vWidth, vHeight);
        drawEyeConnector(vWidth, vHeight);
        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorFlip();
            }
        });
    }

    public void drawHead(float centerX, float centerY, float radiusX, float radiusY) {
        mCanvas.drawOval(centerX - radiusY, centerY - radiusX, centerX + radiusY,
                centerY + radiusX, mHeadPaint);
    }

    public void drawRightEye (int mWidth, int mHeight) {
        mCanvas.drawCircle(mWidth / 2 - 200,mHeight / 2, 80, mCirclePaint);
    }

    public void drawLeftEye (int mWidth, int mHeight) {
        mCanvas.drawCircle(mWidth / 2 + 200,mHeight / 2, 80, mCirclePaint);
    }

    public void drawEyeConnector (int mWidth, int mHeight) {
        mCanvas.drawRect(mWidth / 2 - 175, mHeight / 2 + 20,
                mWidth / 2 + 175, mHeight / 2 -20, mCirclePaint);
    }

    public void animatorFlip(){
        mAnimatorFadeIn.setDuration(1000);
        mAnimatorFadeIn.start();

        mAnimatorFlip.setDuration(3000);
        mAnimatorFlip.setStartDelay(1000);
        mAnimatorFlip.getStartDelay();
        mAnimatorFlip.start();

        mAnimatorFadeOut.setStartDelay(4000);
        mAnimatorFadeOut.getStartDelay();
        mAnimatorFadeOut.setDuration(1000);
        mAnimatorFadeOut.start();
    }

}