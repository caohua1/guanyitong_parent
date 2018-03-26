package com.guanyitong.model.vo;
import com.guanyitong.model.BackMoney;

import java.io.Serializable;
import java.util.List;

public class BackMoneyVo  implements Serializable {
    private String NO;//产品名称
    private  String allBj;//应还的所有的本金
    private String allLx;//应还所有的利息
    private String allBjAndLx;//应还本息
    private List<BackMoney> backMoneyList;


    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getAllBj() {
        return allBj;
    }

    public void setAllBj(String allBj) {
        this.allBj = allBj;
    }

    public String getAllLx() {
        return allLx;
    }

    public void setAllLx(String allLx) {
        this.allLx = allLx;
    }

    public String getAllBjAndLx() {
        return allBjAndLx;
    }

    public void setAllBjAndLx(String allBjAndLx) {
        this.allBjAndLx = allBjAndLx;
    }

    public List<BackMoney> getBackMoneyList() {
        return backMoneyList;
    }

    public void setBackMoneyList(List<BackMoney> backMoneyList) {
        this.backMoneyList = backMoneyList;
    }
}
