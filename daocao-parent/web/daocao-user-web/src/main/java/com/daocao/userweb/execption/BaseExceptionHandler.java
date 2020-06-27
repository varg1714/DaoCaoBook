package com.daocao.userweb.execption;

import com.daocao.exception.DataOperateException;
import com.daocao.exception.SolrOperateException;
import com.daocao.myentity.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author varg
 * @date 2020/4/11 23:17
 */

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointHandler() {
        return new ResponseEntity<>(false, "获取信息失败,请稍后再试！");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> illegalArgumentHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            if (!errors.isEmpty()) {
                String message = errors.get(0).getDefaultMessage();
                return new ResponseEntity<>(false, "参数非法:" + message);
            }
        }
        return new ResponseEntity<>(false, "参数验证错误,请检查后重试");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentHandler(IllegalArgumentException e) {
        return new ResponseEntity<>(false, "参数非法：" + e.getMessage());
    }

    @ExceptionHandler(SolrOperateException.class)
    public ResponseEntity<String> solrExceptionHandler(SolrOperateException e) {
        return new ResponseEntity<>(false, e.getMessage());
    }

    @ExceptionHandler(DataOperateException.class)
    public ResponseEntity<String> dateOperateFailHandler(DataOperateException e) {
        return new ResponseEntity<>(false, e.getMessage());
    }
}
