package com.guanyitong.model;

import java.io.Serializable;
//宝箱实体类（0红包/1现金券/2体验金/3加息券/4提货券）
public class Treasure implements Serializable {
    private Long id;
    private String NO;//编号
    private Integer type;
    private Integer money;
    private String content;
    private Integer guandou;//兑换需要的冠豆数
    private Integer inventory;//库存
    private Integer saleNum;//销量
    private Integer days;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGuandou() {
        return guandou;
    }

    public void setGuandou(Integer guandou) {
        this.guandou = guandou;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
