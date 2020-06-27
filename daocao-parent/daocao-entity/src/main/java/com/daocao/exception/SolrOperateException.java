package com.daocao.exception;

/**
 * @author varg
 * @date 2020/5/15 16:39
 * 如果Solr操作失败的话，将IO异常和Client异常转化为运行时异常抛出
 */
public class SolrOperateException extends RuntimeException {

    public SolrOperateException(String message) {
        super(message);
    }

    public SolrOperateException() {
    }
}
