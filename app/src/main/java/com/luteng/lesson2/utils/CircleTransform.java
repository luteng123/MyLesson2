package com.luteng.lesson2.utils;

import android.graphics.*;
import com.squareup.picasso.Transformation;

/**
 * 圆形转换的工具类
 * Created by John on 2015/12/29.
 */
public class CircleTransform implements Transformation{
    @Override
    public Bitmap transform(Bitmap source) {
        //没个元素占有8个字位，RGB565 两个字节；
        Bitmap result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();//画笔
        //CLAMP 三种属性REPEAT 重复的，镜像：正一张，反一张，CLAMP：三条黑线
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //Math.min(source.getWidth,source.getHeight);//防止不是正方形
        new Canvas(result).drawCircle(source.getWidth() / 2, source.getHeight() / 2,
                Math.max(source.getWidth(),source.getHeight()) / 2, paint);
        //result.getByteCount()//内存中的大小
        //int i = result.getRowBytes() * result.getHeight();

        //！！！！！！原图没有用了，需要释放掉，不然占用资源，内存吃紧
        source.recycle();
        return result;
    }

    @Override
    public String key() {
        return "circle";
    }
}
