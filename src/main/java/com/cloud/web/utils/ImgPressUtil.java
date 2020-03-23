//package com.cloud.web.utils;
//
///*
// *  Creat by zhoudi on 2019/8/9.
// */
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Random;
//
//public class ImgPressUtil {
//
//    public static void testPressImg() throws IOException {
//
//        //创建图片文件(此处为1024×768px的图片)和处理后的图片文件
//        File fromPic = new File("picture/测试图片1024px-768px.jpg");
//        File toPic = new File("picture/结果图片.jpg");
//        File waterPic = new File("picture/水印图片.jpg");//作为水印的图片
//
//        //按指定大小把图片进行缩和放（会遵循原图高宽比例）
//        //此处把图片压成400×500的缩略图
//        Thumbnails.of(fromPic).size(400, 500).toFile(toPic);//变为400*300,遵循原图比例缩或放到400*某个高度
//
//        //按照比例进行缩小和放大
//        Thumbnails.of(fromPic).scale(0.2f).toFile(toPic);//按比例缩小
//        Thumbnails.of(fromPic).scale(2f);//按比例放大
//
//        //不按比例，就按指定的大小进行缩放
//        Thumbnails.of(fromPic).size(100, 100).keepAspectRatio(false).toFile(toPic);
//        //或者Thumbnails.of(fromPic).forceSize(100,100).toFile(toPic);
//
//        //旋转图片，rotate(角度),正数则为顺时针，负数则为逆时针
//        Thumbnails.of(fromPic).size(200, 200).rotate(90).toFile(toPic);
//
//        //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
//        Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(toPic);
//
//        //给图片加水印，watermark(位置，水印图，透明度)Positions.CENTER表示加在中间
//        Thumbnails.of(fromPic).size(400, 400).watermark(Positions.CENTER, ImageIO.read(waterPic), 0.5f).outputQuality(0.8f).toFile(toPic);
//
//        //用sourceRegion()实现图片裁剪
//        //图片中心300*300的区域,Positions.CENTER表示中心，还有许多其他位置可选
//        Thumbnails.of(fromPic).sourceRegion(Positions.CENTER, 300, 300)
//                .size(300, 300).toFile(toPic);
//        //图片中上区域300*300的区域
//        Thumbnails.of(fromPic).sourceRegion(Positions.TOP_CENTER, 300, 300)
//                .size(300, 300).toFile(toPic);
//
//        //用outputFormat(图像格式)转换图片格式，保持原尺寸不变
//        Thumbnails.of(fromPic).scale(1f).outputFormat("png")
//                .toFile("picture/png格式的图片.png");
//
//        //输出成文件流OutputStream
//        OutputStream os = new FileOutputStream(toPic);
//        Thumbnails.of(fromPic).size(120, 120).toOutputStream(os);
//        //输出BufferedImage,asBufferedImage()返回BufferedImage
//        BufferedImage bi = Thumbnails.of(fromPic).size(120, 120).asBufferedImage();
//        ImageIO.write(bi, "jpg", toPic);
//
//        //压缩至指定图片尺寸（例如：横400高300），保持图片不变形，多余部分裁剪掉(这个是引的网友的代码)
//        BufferedImage image = ImageIO.read(fromPic);
//        Thumbnails.Builder<BufferedImage> builder = null;
//
//        int imageWidth = image.getWidth();
//        int imageHeitht = image.getHeight();
//        if ((float) 300 / 400 != (float) imageWidth / imageHeitht) {
//            if (imageWidth > imageHeitht) {
//                image = Thumbnails.of(fromPic).height(300).asBufferedImage();
//            } else {
//                image = Thumbnails.of(fromPic).width(400).asBufferedImage();
//            }
//            builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, 400, 300).size(400, 300);
//        } else {
//            builder = Thumbnails.of(image).size(400, 300);
//        }
//        builder.outputFormat("jpg").toFile(toPic);
//    }
//
//    /**
//     * 传入文件字节流，将字节还原为文件并压缩，在从新转为字节返回
//     *
//     * @param rount
//     * @return
//     */
//    public static String getFile(byte[] rount) {
//        BufferedOutputStream bos = null;
//        FileOutputStream fos = null;
//        File originFile = null;//原始文件
//        File finalFile = null;//压缩文件
//        String originFilePath = "";//临时原始文件路径
//        String finalFilePath = "";//压缩的临时文件路径
//        try {
//            //获得原始文件路径，将字节流写入到原始文件当中
//            originFilePath = getFilePath();
//            originFile = new File(originFilePath);//原始文件
//            fos = new FileOutputStream(originFile);
//            bos = new BufferedOutputStream(fos);
//            bos.write(rount);//将byte数组写到文件中
//            //获得临时压缩文件路径，将原始文件进行压缩后写入到压缩文件当中
//            finalFilePath = getFilePath();
//            finalFile = new File(finalFilePath);//目标文件
//            //此处把图片压成250×200的缩略图
//            Thumbnails.of(originFile).size(1000, 1000).toFile(finalFile);//变为250*200,遵循原图比例缩或放到250*某个高度
//        } catch (Exception e) {
//            e.printStackTrace();
//            deleteFile(originFilePath);
//        } finally {
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    deleteFile(originFilePath);
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                    deleteFile(originFilePath);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    deleteFile(originFilePath);
//                }
//            }
//        }
//        return finalFilePath;
//    }
//
//    //获得临时文件路径
//    public static String getFilePath() throws Exception {
//        String fileDir = initTempDirectory();
//        String fileName = generateFileName() + ".jpg";
//        String filePath = fileDir + "\\" + fileName;
//        return filePath;
//    }
//
//    //生成一个java和linux都能用的临时文件
//    private static String initTempDirectory() throws Exception {
//        File file = null;
//        try {
//            file = File.createTempFile("tmp", null);
//            String parentDirectory = file.getParent();
//            if (parentDirectory == null)
//                return ("");
//            else
//                return parentDirectory;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception("");
//        } finally {
//            if (file != null) {
//                file.delete();
//                file = null;
//            }
//        }
//    }
//
//    // **以时间生成文件名方法(解决相同文件和并发上传的问题)
//    private static String generateFileName() {
//        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 指定格式
//        String formDate = format.format(new Date());// 格式化当前时间
//        int random = new Random().nextInt(1000000);// 生成一个四位数的随机数
//        String fileName = formDate + random;
//        return fileName;
//    }
//
//    //将文件转换成Byte数组
//    public static byte[] getBytesByFile(String pathStr) {
//        File file = new File(pathStr);
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = fis.read(b)) != -1) {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            byte[] data = bos.toByteArray();
//            bos.close();
//            return data;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 删除单个文件
//     *
//     * @param fileName 要删除的文件的文件名
//     * @return 单个文件删除成功返回true，否则返回false
//     */
//    public static boolean deleteFile(String fileName) {
//        File file = new File(fileName);
//        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
//        if (file.exists() && file.isFile()) {
//            if (file.delete()) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }
//}
