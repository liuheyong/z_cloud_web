package com.cloud.web.globalexceptionhandler;

import com.cloud.commons.constants.Constants;
import com.cloud.commons.response.Result;
import com.cloud.web.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LiuHeYong
 * @create: 2019-05-31
 * @description: GlobalExceptionHandler
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @date: 2019/5/31
     * @param: [request, e]
     * @return: org.springframework.web.servlet.ModelAndView
     * @description: exceptionErrorHandler
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(Constants.DEFAULT_ERROR_VIEW);
        return mav;
    }

    /**
     * @date: 2019/5/31
     * @param: [request, e]
     * @return: Result
     * @description: myExceptionErrorHandler
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myExceptionErrorHandler(HttpServletRequest request, MyException e) throws Exception {
        Result result = new Result();
        result.setResultMessage(e.getMessage());
        result.setResultCode(Constants.RESULT_FAIL);
        result.setResultData("MyException");
        result.setUrl(request.getRequestURL().toString());
        return result;
    }

}
