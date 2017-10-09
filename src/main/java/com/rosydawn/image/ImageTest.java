package com.rosydawn.image;

import com.rosydawn.util.ImageUtils;

public class ImageTest {
    public static void main(String[] args) {
        ImageUtils.cropImage("F:/components/tesseract/test/25212303.tif"
                , "F:/components/tesseract/test/generated/25212303.tif"
                , 300, 300, 1800, 2900);
    }
}
