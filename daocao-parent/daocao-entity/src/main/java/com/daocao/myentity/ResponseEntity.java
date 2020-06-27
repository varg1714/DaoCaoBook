package com.daocao.myentity;

/**
 * @author varg
 * @date 2020/5/4 20:10
 */
public class ResponseEntity<T> {

    private String code;
    private boolean status;
    private String message;
    private T entity;

    public ResponseEntity(boolean status,String message,T entity){
        this.status = status;
        this.message = message;
        this.entity = entity;
    }

    public ResponseEntity(boolean status,String message){
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code='" + code + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}
