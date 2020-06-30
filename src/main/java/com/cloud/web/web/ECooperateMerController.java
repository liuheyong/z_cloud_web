package com.cloud.web.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.cloud.commons.constants.Constants;
import com.cloud.commons.dto.ECooperateMer;
import com.cloud.commons.response.QueryECooperateMerResponse;
import com.cloud.commons.response.Result;
import com.cloud.commons.service.ECooperateMerService;
import com.cloud.commons.utils.CloudUtils;
import com.cloud.service.utils.UUIDUtil;
import com.cloud.web.myannotation.AopTest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: LiuHeYong
 * @create: 2019-05-22
 * @description: ECooperateMerController
 **/
@Controller
public class ECooperateMerController extends DefaultController {

    public static final Logger logger = LoggerFactory.getLogger(ECooperateMerController.class);

    @Reference(check = false, version = "${dubbo.service.version}", timeout = 60000)
    private ECooperateMerService eCooperateMerService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @Date: 2019-11-24
     * @Param: []
     * @return: com.cloud.commons.dto.ECooperateMer
     * @Description: 前置方法
     */
    //@ModelAttribute("eCooperateMer")
    //public ECooperateMer getECooperateMer() {
    //    logger.info("前置方法 ================= @ModelAttribute");
    //    return new ECooperateMer("E201902021245", "A201902021245", "测试分销商名称");
    //}

    /**
     * @date: 2019/5/24
     * @param: [eCooperateMer]
     * @return: Result
     * @description: 新增详情
     */
    @ResponseBody
    @RequestMapping(value = Constants.CLOUD + "/addECooperateMerInfo")
    public Result addECooperateMerInfo(ECooperateMer eCooperateMer) {
        Result result = new Result();
        Map<String, Object> model = new HashMap<String, Object>(4);
        try {
            ECooperateMer mer = new ECooperateMer(UUIDUtil.getUNIDX("EC", 30), "A2019022200000001", "测试数据添加", "1556442573307.jpg", "https://www.baidu.com", "1", 12);
            eCooperateMerService.addECooperateMerInfo(mer);
            result.setResultData(model);
            result.setResultCode(Constants.RESULT_SUCCESS);
        } catch (Exception e) {
            logger.error("系统异常", e);
            result.setResultCode(Constants.RESULT_FAIL);
            result.setResultMessage("系统异常");
        }
        return result;
    }

    /**
     * @date: 2019/5/24
     * @param: [eCooperateMer]
     * @return: Result
     * @description: 详情
     */
    @ResponseBody
    @RequestMapping(value = Constants.CLOUD + "/queryECooperateMerInfo")
    public Result queryECooperateMerInfo(ECooperateMer eCooperateMer) {
        Result result = new Result();
        Map<String, Object> model = new HashMap<String, Object>(4);
        try {
            Optional<ECooperateMer> optDto =
                    Optional.ofNullable(eCooperateMerService.queryECooperateMerInfo(eCooperateMer));
            if (optDto.isPresent()) {
                model.put("eCooperateMer", optDto.get());
                result.setResultData(model);
                result.setResultCode(Constants.RESULT_SUCCESS);
                return result;
            } else {
                throw new Exception("该eCooperateMer不存在");
            }
        } catch (Exception e) {
            logger.error("系统异常", e);
            result.setResultCode(Constants.RESULT_FAIL);
            result.setResultMessage("系统异常");
        }
        return result;
    }

    /**
     * @date: 2019/5/27
     * @param: [eCooperateMer]
     * @return: com.boot.com.alibabacloud.commons.response.Result
     * @description: 创建线程查询列表
     */
    @AopTest
    @RequestMapping(value = Constants.CLOUD + "/queryECooperateMerListPage", method = {RequestMethod.POST,
            RequestMethod.GET})
    public String queryECooperateMerListPage(@ModelAttribute ECooperateMer eCooperateMer, ModelMap model) throws InterruptedException {
        //String sessionID = request.getSession(false).getId();
        //request.getSession().setMaxInactiveInterval(1000 * 60 * 30);
        //logger.info("=================sessionID:" + sessionID + "====================");
        //Cookie cookie = new Cookie("cookieSess", sessionID);
        //response.addCookie(cookie);
        //Thread currentThread = Thread.currentThread();
        //synchronized (currentThread) {
        //    currentThread.wait(2);
        //}
        Result result = new Result();
        try {
            //创建线程执行任务
            //Runnable runnable = new Runnable() {
            //    @Override
            //    public void run() {
            //        try {
            //            eCooperateMerService.queryECooperateMerListPage(eCooperateMer);
            //        } catch (Exception e) {
            //            logger.error("系统异常", e);
            //            return;
            //        }
            //    }
            //};
            //创建线程池
            //ExecutorService executorService = Executors.newFixedThreadPool(25);
            //for (int i = 0; i < Constants.NUMBER_100; i++) {
            //    executorService.submit(runnable);
            //}
            RpcContext.getContext().setAttachment("myKey", "myValue");
            QueryECooperateMerResponse response = eCooperateMerService.queryECooperateMerListPage(eCooperateMer);
            model.put("eCooperateMerList", response.geteCooperateMerList());
            //redisTemplate.opsForValue().set("eCooperateMerList", response.geteCooperateMerList());
            //单个对象
            //ECooperateMer mer = new ECooperateMer();
            //mer.setCooperateMerSeq("EC2019042800000023");
            //Optional<ECooperateMer> optDto = Optional.ofNullable(eCooperateMerService.queryECooperateMerInfo(mer));
            //if (optDto.isPresent()) {
            //    model.put("eCooperateMer", optDto.get());
            //} else {
            //    throw new Exception("该eCooperateMer不存在");
            //}
            result.setResultCode(Constants.RESULT_SUCCESS);
            result.setResultData(model);
        } catch (Exception e) {
            logger.error("系统异常", e);
            result.setResultCode(Constants.RESULT_FAIL);
            result.setResultMessage("系统异常");
        }
        return "e_cooperate_mer_list_page";
    }

    @ResponseBody
    @RequestMapping(value = Constants.CLOUD + "/exportECooperateMerList")
    public Result exportECooperateMerList(ECooperateMer eCooperateMer, HttpServletResponse res) {
        Result result = new Result();
        try {
            QueryECooperateMerResponse response = eCooperateMerService.queryECooperateMerListPage(eCooperateMer);
            if (response != null && CollectionUtils.isNotEmpty(response.geteCooperateMerList())) {
                //1.创建工作簿
                HSSFWorkbook workbook = new HSSFWorkbook();
                //标题
                CellRangeAddress callRangeAddress1 = new CellRangeAddress(0, 1, 0, 0);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddress2 = new CellRangeAddress(0, 1, 1, 1);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddress3 = new CellRangeAddress(0, 1, 2, 2);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddress4 = new CellRangeAddress(0, 1, 3, 3);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddress5 = new CellRangeAddress(0, 1, 4, 4);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddress6 = new CellRangeAddress(0, 1, 5, 5);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddress7 = new CellRangeAddress(0, 1, 6, 6);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddress8 = new CellRangeAddress(0, 1, 7, 7);//起始行,结束行,起始列,结束列

                //标题样式
                HSSFCellStyle headerStyle = CloudUtils.createCellStyle(workbook, (short) 10, true, true);
                //内容样式
                HSSFCellStyle contentStyle = CloudUtils.createCellStyle(workbook, (short) 10, false, true);

                //2.创建工作表
                HSSFSheet sheet = workbook.createSheet("合作商户列表导出");

                sheet.addMergedRegion(callRangeAddress1);
                sheet.addMergedRegion(callRangeAddress2);
                sheet.addMergedRegion(callRangeAddress3);
                sheet.addMergedRegion(callRangeAddress4);
                sheet.addMergedRegion(callRangeAddress5);
                sheet.addMergedRegion(callRangeAddress6);
                sheet.addMergedRegion(callRangeAddress7);
                sheet.addMergedRegion(callRangeAddress8);
                //设置默认列宽
                sheet.setDefaultColumnWidth(15);

                //3 创建列标题，并且设置列标题
                HSSFRow row2 = sheet.createRow(0);
                String[] titles = {"序号", "商户编号", "分销商编号", "商户名称", "图片链接", "商户链接"};//""为占位字符串
                for (int i = 0; i < titles.length; i++) {
                    HSSFCell cell2 = row2.createCell(i);
                    //加载单元格样式
                    cell2.setCellStyle(headerStyle);
                    cell2.setCellValue(titles[i]);
                }

                //4.操作单元格，将列表写入excel
                List<ECooperateMer> eCooMerList = response.geteCooperateMerList();
                int i = 1;
                for (int j = 0; j < eCooMerList.size(); j++) {

                    HSSFRow row3 = sheet.createRow(2 + j);

                    HSSFCell cell0 = row3.createCell(0);
                    cell0.setCellStyle(contentStyle);
                    cell0.setCellValue(i++);

                    HSSFCell cell1 = row3.createCell(1);
                    cell1.setCellStyle(contentStyle);
                    cell1.setCellValue(eCooMerList.get(j).getCooperateMerSeq());

                    HSSFCell cell2 = row3.createCell(2);
                    cell2.setCellStyle(contentStyle);
                    cell2.setCellValue(eCooMerList.get(j).getAgentMerSeq());

                    HSSFCell cell3 = row3.createCell(3);
                    cell3.setCellStyle(contentStyle);
                    cell3.setCellValue(eCooMerList.get(j).getMerName());

                    HSSFCell cell4 = row3.createCell(4);
                    cell4.setCellStyle(contentStyle);
                    cell4.setCellValue(eCooMerList.get(j).getImageLink());

                    HSSFCell cell5 = row3.createCell(5);
                    cell5.setCellStyle(contentStyle);
                    cell5.setCellValue(eCooMerList.get(j).getMerLink());


                }
                //FileOutputStream out = new FileOutputStream("/export_excel/合作商户列表.xlsx");
                //5.输出
                //workbook.write(out);

                downloadExcelToBrowser(res, workbook, "合作商户列表.xlsx");

                result.setResultCode(Constants.RESULT_SUCCESS);
            } else {
                logger.debug("导出数据为空！");
                result.setResultCode(Constants.RESULT_FAIL);
                result.setResultMessage("导出数据为空！");
            }
        } catch (Exception e) {
            logger.error("系统异常", e);
            result.setResultCode(Constants.RESULT_FAIL);
            result.setResultMessage("系统异常");
        }
        return result;
    }

    /**
     * @Date: 2019-10-22
     * @Description: 导出Excel浏览器显示下载框
     */
    public static void downloadExcelToBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) throws IOException {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes(), "iso-8859-1"));
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

    /**
     * @Date: 2019-12-28
     * @Param: [request, response]
     * @Description: 输出txt到浏览器 文件较小
     */
    @ResponseBody
    @RequestMapping(value = Constants.CLOUD + "/exportStringToTxt01")
    public void exportStringToTxt01(HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "测试txt01";
        response.setContentType("text/plain");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        BufferedOutputStream buff = null;
        //可以定义换行
        //String enter = "\r\n";
        ServletOutputStream outSTr = null;
        try {
            outSTr = response.getOutputStream();
            buff = new BufferedOutputStream(outSTr);
            // write.append(enter);
            sb.append("MifSx7ImNvZGUiOiJQUyIsImZhbGxiYWNrRGF0ZSI6IjIwMTktMTItMTQiLCJwYWlVXbyI" +
                    "6IjIwMjAtMTItMTMifSx7ImNvZGUiOiJHTyIsImZhbGxiYWNrRGF0ZSI6IjIwMTktMTItMTQL" +
                    "CJwYWlkVXBUbyI6IjIwMjAtMTItMTMifSx7ImNvZGUiOiJETSIsImZhbGxiYWNrRGF0ZSI6IjI" +
                    "FpZFVwVG8iOiIyMDIwLTEyLTEzIn0seyJjb2RlIjoiREMiLCJmYWxsYmFja0RhdGUiOiIyME5LE" +
                    "FpZFVwVG8iOiIyMDIwLTEyLTEzIn0seyJjb2RlIjoiUlNVIiwiZmFsbGJhY2tEYXRlIjoiMjAxS0x");
            buff.write(sb.toString().getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = Constants.CLOUD + "/exportStringToTxt02")
    public void exportStringToTxt(HttpServletResponse response, String in) throws UnsupportedEncodingException {
        String fileName = "测试txt01";
        response.setContentType("text/plain");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        BufferedInputStream bis = null;
        BufferedOutputStream buff = null;
        try {
            buff = new BufferedOutputStream(response.getOutputStream());
            buff.write(in.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
