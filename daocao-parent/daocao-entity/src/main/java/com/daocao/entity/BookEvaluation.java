package com.daocao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史订单对出售中的书籍的评价
 * @author varg
 */

public class BookEvaluation implements Serializable {

    private String nickName;
    private String headPic;
    private String userLevel;
    private String evaluation;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date evaluationDate;
    private String evalType;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getEvalType() {
        return evalType;
    }

    public void setEvalType(String evalType) {
        this.evalType = evalType;
    }

    @Override
    public String toString() {
        return "BookEvaluation{" +
                "nickName='" + nickName + '\'' +
                ", headPic='" + headPic + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", evaluationDate=" + evaluationDate +
                ", evalType='" + evalType + '\'' +
                '}';
    }
}
