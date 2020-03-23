package com.cloud.web.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * .<br>
 * [类名] WaterImage <br>
 * [描述] 给图片添加水印 <br>
 * [作者] zj <br>
 * [时间] 2014-9-24 下午5:57:28 <br>
 * ******************************************************** .<br>
 */
public class WaterImage {

    public static final Logger log = LoggerFactory.getLogger(WaterImage.class);

    public static BufferedImage markImageBySingleIcon(InputStream is, InputStream file, Integer degree) throws Exception {
        try {
            // 将icon加载到内存中
            Image ic = ImageIO.read(is);
            // icon宽度
            int icWidth = ic.getWidth(null);
            // icon高度
            int icHeight = ic.getHeight(null);
            // 将源图片读到内存中
            Image img = ImageIO.read(file);
            // 图片宽
            int width = img.getWidth(null);
            // 图片高
            int height = img.getHeight(null);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 创建一个指定 BufferedImage 的 Graphics2D 对象
            Graphics2D g = bi.createGraphics();
            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            // 呈现一个图像，在绘制前进行从图像空间到用户空间的转换
            g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            if (null != degree) {
                // 设置水印旋转
                g.rotate(Math.toRadians(degree), (double) bi.getWidth() / 2, (double) bi.getHeight() / 2);
            }
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            // ImageIcon imgIcon = new ImageIcon(icon);
            // 得到Image对象。
            // Image con = imgIcon.getImage();
            // 透明度，最小值为0，最大值为1
            float clarity = 1f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clarity));
            // x,y轴默认是从0坐标开始
            int x = width - icWidth - 20;
            int y = height - icHeight - 20;
            g.drawImage(ic, x, y, icWidth, icHeight, null);// 表示水印图片的坐标位置(x,y)
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            return bi;
        } catch (Exception e) {
            log.error("图片水印失败", e);
            throw new Exception("图片水印失败");
        }
    }

    public static void main(String[] args) throws Exception {
        String icon = "//Users//wenyixicodedog//常用图片//111.png";
        String source = "//Users//wenyixicodedog//常用图片//9.jpeg";
        String output = "//Users//wenyixicodedog//常用图片";
        String imageName = "makeWater";
        String imageType = "png";
        Integer degree = null;
        File fileIcon = new File(icon);
        File fileSource = new File(source);
        try {
            BufferedImage bi = WaterImage.markImageBySingleIcon(new FileInputStream(fileIcon), new FileInputStream(fileSource), degree);
            File sf = new File(output, imageName + "." + imageType);
            ImageIO.write(bi, imageType, sf); // 保存图片
        } catch (Exception e) {
            log.error("图片水印失败", e);
            throw new Exception("图片水印失败");
        }
        System.out.println("===========水印完成");
    }
}
