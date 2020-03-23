//package com.cloud.web.utils.pdf;
//
//import com.bee.framework.i.bp.core.CoreException;
//import com.itextpdf.awt.geom.Rectangle2D;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.parser.ImageRenderInfo;
//import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
//import com.itextpdf.text.pdf.parser.RenderListener;
//import com.itextpdf.text.pdf.parser.TextRenderInfo;
//import com.itextpdf.tool.xml.XMLWorkerFontProvider;
//import com.itextpdf.tool.xml.XMLWorkerHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
//public class PDFUtils {
//    private static final Logger log = LoggerFactory.getLogger(PDFUtils.class);
//
//    public static Font setChineseFont() {
//        BaseFont bf = null;
//        Font fontChinese = null;
//        try {
//            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//            fontChinese = new Font(bf, 12, Font.NORMAL);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fontChinese;
//    }
//
//    /**
//     *
//     *********************************************************.<br>
//     * @method createPDF <br>
//     * @return void <br>
//     * @author fengjianjun <br>
//     * @created 2016年10月19日 下午2:01:37 <br>
//     *********************************************************.<br>
//     */
//    public static byte[] createPDF(String baseImgMy,String baseImgOther,String otherCompanyName,String agreement) throws CoreException {
//        XMLWorkerHelper worker = null;
//        PdfWriter pdfWriter = null;
//        String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());//签章日期
//        try {
//            Document document = new Document();
//            document.setPageSize(PageSize.A4);
//            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//            pdfWriter = PdfWriter.getInstance(document,byteArray);
//            document.open();
//            worker = XMLWorkerHelper.getInstance();
//            worker.parseXHtml(pdfWriter, document, new ByteArrayInputStream(agreement.getBytes()),Charset.forName("UTF-8"), new AsianFontProvider());// (pdfWriter
//            byte[] buffer = Base64.getDecoder().decode(baseImgMy.getBytes());
//            if(baseImgOther == null){
//                throw new CoreException("领签失败，请检查授权书图片是否含有公章");
//            }
//            byte[] buffers = Base64.getDecoder().decode(baseImgOther.getBytes());
//            Image image = Image.getInstance(buffer);
//            Image images = Image.getInstance(buffers);
//            image.setAlignment(Image.LEFT | Image.UNDERLYING);
//            image.scaleAbsolute(100, 100);
//            Chunk cks = new Chunk(image, 0, -80);
//            Paragraph paragraph = new Paragraph("",setChineseFont());
//            paragraph.setKeepTogether(true);
//            paragraph.add(cks);
//            images.setAlignment(Image.LEFT | Image.UNDERLYING);
//            images.scaleAbsolute(100, 100);
//            Chunk ck = new Chunk(images, 300, -80);
//            paragraph.add(ck);
//            String name = "北京云网四方信息技术有限公司";
//            String name1 = otherCompanyName;
//            String blank="";
//            for(int i = 0;i<111-name.getBytes().length-name1.getBytes().length;i++){
//                blank+=" ";
//            }
//            paragraph.add(new Paragraph("甲方：" + name +blank
//                    + "乙方："+name1,setChineseFont()));
//            paragraph.add(new Paragraph("  "+date +"                                                                                                                                "
//                    + date,new Font(setChineseFont())));
//            document.add(paragraph);
//            document.close();
//            return byteArray.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            throw new CoreException("领签失败",e);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//            throw new CoreException("领签失败",e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new CoreException("领签失败",e);
//        }
//    }
//
//    /**
//     * @description: 徽商电子盖章
//     * @auther: feng
//     * @date: 2018/11/13 13:56
//     * @param: [baseImgMy, baseImgOther, otherCompanyName, agreement]
//     * @return: byte[]
//     * @throws:
//     **/
//    public static byte[] pdfForhuishang(String baseImgMy,String agreement,String platCompanyName) throws CoreException {
//        XMLWorkerHelper worker = null;
//        PdfWriter pdfWriter = null;
//        String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());//签章日期
//        try {
//            Document document = new Document();
//            document.setPageSize(PageSize.A4);
//            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//            pdfWriter = PdfWriter.getInstance(document,byteArray);
//            document.open();
//            worker = XMLWorkerHelper.getInstance();
//            worker.parseXHtml(pdfWriter, document, new ByteArrayInputStream(agreement.getBytes()),Charset.forName("UTF-8"), new AsianFontProvider());// (pdfWriter,
//            byte[] buffer = Base64.getDecoder().decode(baseImgMy.getBytes());
//            Image image = Image.getInstance(buffer);
//
//            image.setAlignment(Image.LEFT | Image.UNDERLYING);
//            image.scaleAbsolute(100, 100);
//            Chunk cks = new Chunk(image, 430, -80);
//            Paragraph paragraph = new Paragraph("",setChineseFont());
//            paragraph.setKeepTogether(true);
//            paragraph.add(cks);
//
//            Paragraph p1 = new Paragraph(date,new Font(setChineseFont()));
//            p1.setAlignment(Element.ALIGN_RIGHT);
//            Paragraph p2 = new Paragraph(platCompanyName,new Font(setChineseFont()));
//            p2.setAlignment(Element.ALIGN_RIGHT);
//
//            Paragraph p3 = new Paragraph("",new Font(setChineseFont()));
//            p2.setAlignment(Element.ALIGN_RIGHT);
//
//            paragraph.add(p1);
//            paragraph.add("\n");
//            paragraph.add(p2);
//            document.add(paragraph);
//            document.close();
//            byte [] filebyte = byteArray.toByteArray();
//            byteArray.flush();
//            byteArray.close();
//            return filebyte;
//
//        } catch (Exception e) {
//            log.error("",e);
//            throw new CoreException("盖章失败",e);
//        }
//
//    }
//    /**
//     * @description: 图片转pdf
//     * @auther: feng
//     * @date: 2018/11/14 13:44
//     * @param: [baseImgMy]
//     * @return: byte[]
//     * @throws:
//     **/
//    public static byte[] imgtopdf(String baseImgMy) throws CoreException {
//        XMLWorkerHelper worker = null;
//        PdfWriter pdfWriter = null;
//        try {
//            Document document = new Document();
//            document.setPageSize(PageSize.A5);
//            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//            pdfWriter = PdfWriter.getInstance(document,byteArray);
//            document.open();
//            byte[] buffer = Base64.getDecoder().decode(baseImgMy.getBytes());
//            Image image = Image.getInstance(buffer);
//
//            image.setAlignment(Image.LEFT | Image.UNDERLYING);
//            image.scaleAbsolute(300, 300);
//            Chunk cks = new Chunk(image, 100, -200);
//            Paragraph paragraph = new Paragraph("",setChineseFont());
//            paragraph.setKeepTogether(true);
//            paragraph.add(cks);
//            document.add(paragraph);
//            document.close();
//            return byteArray.toByteArray();
//        }catch (Exception e) {
//            log.error("",e);
//            throw new CoreException("盖章失败",e);
//        }
//    }
//    /**
//     * @description: createPDF1 为tw签署生成pfd格式的合同 并返回两个合同章的坐标 pdf样式需要参考 test.html 否则章的位置不好计算
//     * @auther: feng
//     * @date: 2018/11/22 13:23
//     * @param: [agreement, firstCompanyName, secCompanyName]
//     * @return: com.bee.utils.common.PDFResultCommons
//     * @throws: CoreException
//     **/
//    public static PDFResultCommons createPDF1(byte [] agreement,List<String> companyNameList) throws CoreException {
//
//        PDFResultCommons pdfResultCommons = new PDFResultCommons();
//        XMLWorkerHelper worker = null;
//        PdfWriter pdfWriter = null;
//        List<float[]> list = new ArrayList<float[]>(2);
//        try {
//            if (agreement == null || agreement.length == 0) {
//                throw new CoreException("合同内容");
//            }
//            if (companyNameList.size() == 0) {
//                throw new CoreException("签署的公司名称，最少是1个");
//            }
//            Document document = new Document();
//            document.setPageSize(PageSize.A4);
//            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//            pdfWriter = PdfWriter.getInstance(document, byteArray);
//            document.open();
//            worker = XMLWorkerHelper.getInstance();
//            worker.parseXHtml(pdfWriter, document, new ByteArrayInputStream(agreement), Charset.forName("UTF-8"), new AsianFontProvider());// (pdfWriter
//            float y = document.getPageSize().getHeight();
//            float x = document.getPageSize().getWidth();
//            document.close();
//            byte pdfbyte[] = byteArray.toByteArray();
//            byteArray.flush();
//            byteArray.close();
//
//            //读取pdf
//            PdfReader pdfReader = new PdfReader(pdfbyte);
//            //获取pdf总页数
//            int totalPageNum = pdfReader.getNumberOfPages();
//
//            for (int j = 1; j <= totalPageNum; j++) {
//
//                PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
//                int finalJ = j;
//                pdfReaderContentParser.processContent(j, new RenderListener() {
//                    @Override
//                    public void renderText(TextRenderInfo textRenderInfo) {
//
//                        String text = textRenderInfo.getText();
//                        for (int i = 0; i < companyNameList.size(); i++) {
//
//                            if (null != text && text.contains(companyNameList.get(i))) {
//                                System.out.println(text);
//                                Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
//                                float[] resu = new float[3];
//                                resu[0] = boundingRectange.x;
//                                resu[1] = boundingRectange.y;
//                                resu[2] = finalJ;
//                                list.add(resu);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void renderImage(ImageRenderInfo arg0) {
//                        // TODO Auto-generated method stub
//
//                    }
//
//                    @Override
//                    public void endTextBlock() {
//                        // TODO Auto-generated method stub
//
//                    }
//
//                    @Override
//                    public void beginTextBlock() {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
//        }
//
//
//            //标准章的尺寸  40mm * 40 mm   122是tw页面章的图片大小，这里计算的坐标有细微差别，所以为了计算精准一些，这里使用61来计算偏移量
//
//            //if(list.size() == 0){
//            //    throw new CoreException("没有章");
//            //}
////            for(float [] resu:list){
////                //计算百分比
////                BigDecimal fx = DecimalUtil.divide(new BigDecimal(resu[0]-20), new BigDecimal(x), 2);
////                BigDecimal fy = DecimalUtil.divide(new BigDecimal(y-resu[1]-30), new BigDecimal(y), 2);
////                //签署人的盖章位置list
////                resu[0] = fx.floatValue();
////                resu[1] = fy.floatValue();
////                log.info("===============fx:"+resu[0] +"====================fy:"+resu[1] +"========================page:"+resu[2]);
////                System.out.println("===============fx:"+resu[0] +"====================fy:"+resu[1] +"========================page:"+resu[2]);
////            }
//            pdfReader.close();
//
//            pdfResultCommons.setPostList(list);
//            pdfResultCommons.setPdfbytes(pdfbyte);
//
//            return pdfResultCommons;
//        } catch (CoreException e) {
//            log.error("生成pdf合同失败",e);
//            throw e;
//        }catch (Exception e) {
//            log.error("生成pdf合同失败",e);
//            throw new CoreException("生成pdf合同失败",e);
//        }
//    }
//
//    public static void main(String[] args) {
//        File file = null;
//        try {
//            List<String> companyNameList = new ArrayList<>();
//            companyNameList.add("abc");
//            file = new File("C:\\Users\\liao\\Desktop\\abc.html");
//            FileInputStream fi = new FileInputStream(file);
//            byte b[] = new byte[fi.available()];
//            fi.read(b);
//            fi.close();
//            PDFResultCommons pdf1 = createPDF1(b, companyNameList);
//            FileOutputStream fos = new FileOutputStream(new File("D:\\hg.pdf"));
//            fos.write(pdf1.getPdfbytes());
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
//
//class AsianFontProvider extends XMLWorkerFontProvider {
//    public Font getFont(final String fontname, final String encoding,
//            final boolean embedded, final float size, final int style,
//            final BaseColor color) {
//        BaseFont bf = null;
//        try {
//            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Font font = new Font(bf, size, style, color);
//        font.setColor(color);
//        return font;
//    }
//}
