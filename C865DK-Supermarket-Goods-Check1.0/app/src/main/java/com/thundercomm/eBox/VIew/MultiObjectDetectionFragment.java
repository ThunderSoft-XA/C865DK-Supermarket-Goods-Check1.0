package com.thundercomm.eBox.VIew;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.SurfaceHolder;

import com.thundercomm.eBox.Data.Recognition;

import java.util.List;

import androidx.annotation.Nullable;

public class MultiObjectDetectionFragment extends PlayFragment {
    private static final String TAG = "MultiObjectDetectionFragment";
    MultiBoxTracker tracker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tracker = new MultiBoxTracker(getContext());
    }

    public MultiObjectDetectionFragment(int id) {
        super(id);
    }

    private void draw(final SurfaceHolder mHolder, List<Recognition> results, final int width, final int height) {
        Canvas canvas = null;
        if (mHolder != null) {
            try {
                canvas = mHolder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                tracker.setFrameConfiguration(width, height);
                tracker.trackResults(results);
                tracker.draw(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != canvas) {
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
        hasDrawn = false;
    }


    public void onDraw(List<Recognition> results,final int width, final int height) {
        draw(mFaceViewHolder, results, width, height);
        hasDrawn = true;
    }

}
