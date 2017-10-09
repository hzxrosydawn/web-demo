package com.rosydawn.simple;

import javax.imageio.ImageIO;

public class FormatTest {
    public static void main(String[] args) {
        for(String format : ImageIO.getReaderFormatNames())
            System.out.println(format);
    }
}
