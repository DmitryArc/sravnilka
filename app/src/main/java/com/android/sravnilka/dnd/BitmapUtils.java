package com.android.sravnilka.dnd;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import com.android.sravnilka.R;

class BitmapUtils {

    private BitmapUtils() {
    }

    static Bitmap getBitmapFromView( final View v) {
        View vv = v;
        vv.findViewById(R.id.draganddrop_textview).setBackgroundColor(vv.getResources().getColor(android.R.color.holo_red_dark));
        Bitmap bitmap = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

}
