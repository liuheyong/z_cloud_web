//package com.cloud.web.utils.pdf;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.catalina.mapper.Mapper;
//import org.apache.commons.io.IOUtils;
//
//import java.awt.*;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URL;
//
//public class WordToPdfUtils {
//
//    public static JSONObject convert(String word, String pdf) {
//
//        JSONObject res = new JSONObject();
//
//        OutputStream os = null;
//        try {
//            File wordFile = new File(word);
//            if (!wordFile.exists()) {
//                res.put("code", 404);
//                res.put("message", "word路径不存在！");
//                return res;
//            }
//            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(wordFile);
//            PhysicalFonts.addPhysicalFont(new URL("file:" + WordToPdfUtils.class.getResource("/simsun.ttc").getPath().toString()));
//            Mapper fontMapper = new IdentityPlusMapper();
////            Font font =  Font.createFont(Font.TRUETYPE_FONT,WordToPdfUtils.class.getResourceAsStream("/simsun.ttc"));
////            fontMapper.put("lisu", PhysicalFonts.get("LiSu"));
//            fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
////            fontMapper.put("微软雅黑",PhysicalFonts.get("Microsoft Yahei"));
////            fontMapper.put("黑体",PhysicalFonts.get("SimHei"));
////            fontMapper.put("楷体",PhysicalFonts.get("KaiTi"));
////            fontMapper.put("新宋体",PhysicalFonts.get("NSimSun"));
////            fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
////            fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
////            fontMapper.put("宋体扩展",PhysicalFonts.get("simsun-extB"));
////            fontMapper.put("仿宋",PhysicalFonts.get("FangSong"));
////            fontMapper.put("仿宋_GB2312",PhysicalFonts.get("FangSong_GB2312"));
////            fontMapper.put("幼圆",PhysicalFonts.get("YouYuan"));
////            fontMapper.put("华文宋体",PhysicalFonts.get("STSong"));
////            fontMapper.put("华文中宋",PhysicalFonts.get("STZhongsong"));
//            mlPackage.setFontMapper(fontMapper);
//
//            os = new FileOutputStream(pdf);
//
//            FOSettings foSettings = Docx4J.createFOSettings();
//            foSettings.setWmlPackage(mlPackage);
//            Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
//
//            IOUtils.closeQuietly(os);
//
//            res.put("code", 200);
//            res.put("message", "转换成功");
//            return res;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            res.put("code", 300);
//            res.put("message", e.getMessage());
//            return res;
//        }
//    }
//
//    public static void main(String[] args) throws IOException, FontFormatException {
//        Font font = Font.createFont(Font.TRUETYPE_FONT, WordToPdfUtils.class.getResourceAsStream("/simsun.ttc"));
//        file:
//        System.out.println(font.getFontName());
//    }
//}