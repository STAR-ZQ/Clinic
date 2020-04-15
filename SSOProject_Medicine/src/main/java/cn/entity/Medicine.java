package cn.entity;

import java.util.Date;

public class Medicine {
    private int id;
    private String code;
    private String medName;
    private String norms;
    private String tollType;
    private double costPrice;
    private double salePrice;
    private String factory;
    private int status;
    private String createTime;

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", medName='" + medName + '\'' +
                ", norms='" + norms + '\'' +
                ", tollType='" + tollType + '\'' +
                ", costPrice=" + costPrice +
                ", salePrice=" + salePrice +
                ", factory='" + factory + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getTollType() {
        return tollType;
    }

    public void setTollType(String tollType) {
        this.tollType = tollType;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
