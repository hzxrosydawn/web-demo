package com.rosydawn.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    /**
     * 对图片裁剪，并把裁剪新图片保存。如果srcImagePath或targetImagePath为null，什么都不做。
     *
     * @param srcImagePath    读取源图片路径
     * @param targetImagePath 写入图片路径
     * @param x               剪切起始点x坐标
     * @param y               剪切起始点y坐标
     * @param width           剪切宽度
     * @param height          剪切高度
     */
    public static void cropImage(String srcImagePath, String targetImagePath, int x, int y, int width, int height) {
        if (srcImagePath == null || "".equals(srcImagePath) || targetImagePath == null || "".equals(targetImagePath)) {
            return;
        }

        try {
            BufferedImage srcImage = ImageIO.read(new File(srcImagePath));
            BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            targetImage.getGraphics().drawImage(srcImage, 0, 0, width, height, x, y
                    , x + width, y + height, null);
            ImageIO.write(targetImage, srcImagePath.substring(srcImagePath.lastIndexOf(".")), new File(targetImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取目标图片中的像素，以二维数组的形式返回。
     *
     * @param image 目标图像对象。
     * @return 图像像素的二维表示。
     */
    public static int[][] getPixels(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

    /**
     * 按倍率放大图片
     *
     * @param srcImagePath 读取图形路径
     * @param toImagePath  写入入行路径
     * @param widthRatio   宽度放大比例
     * @param heightRatio  高度放大比例
     * @throws IOException
     */
    public void enlargementImageByRatio(String srcImagePath, String toImagePath, int widthRatio, int heightRatio) throws IOException {
        FileOutputStream out = null;
        try {
            //读入文件
            File file = new File(srcImagePath);
            // 构造Image对象
            BufferedImage src = javax.imageio.ImageIO.read(file);
            int width = src.getWidth();
            int height = src.getHeight();
            // 放大边长
            BufferedImage tag = new BufferedImage(width * widthRatio, height * heightRatio, BufferedImage.TYPE_INT_RGB);
            //绘制放大后的图片
            tag.getGraphics().drawImage(src, 0, 0, width * widthRatio, height * heightRatio, null);
            out = new FileOutputStream(toImagePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * 长高等比例放大图片
     *
     * @param srcImagePath 读取图形路径
     * @param toImagePath  写入入行路径
     * @param ratio        放大比例
     * @throws IOException
     */
    public void enlargementImageEqualProportion(String srcImagePath, String toImagePath, int ratio) throws IOException {
        FileOutputStream out = null;
        try {
            //读入文件
            File file = new File(srcImagePath);
            // 构造Image对象
            BufferedImage src = javax.imageio.ImageIO.read(file);
            int width = src.getWidth();
            int height = src.getHeight();
            // 放大边长
            BufferedImage tag = new BufferedImage(width * ratio, height * ratio, BufferedImage.TYPE_INT_RGB);
            //绘制放大后的图片
            tag.getGraphics().drawImage(src, 0, 0, width * ratio, height * ratio, null);
            out = new FileOutputStream(toImagePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * 图片灰化操作
     *
     * @param srcImage    读取图片路径
     * @param toPath      写入灰化后的图片路径
     * @param imageFormat 图片写入格式
     */
    public void grayImage(String srcImage, String toPath, String imageFormat) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImage));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, imageFormat, new File(toPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
