package com.dtr.config.handler;

import com.dtr.web.dto.ResponseVO;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2021/4/12.
 *
 * @author LiuDong
 */
@ControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 全局异常处理
     * @param ex ex
     * @return ModelAndView
     */
    @ExceptionHandler
    public ModelAndView ExceptionController(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("myerror");
        return mv;
    }

    /**
     * 全局数据绑定
     * @return
     */
    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }

    /**
     * 全局数据预处理
     * @param binder
     */
    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
}
