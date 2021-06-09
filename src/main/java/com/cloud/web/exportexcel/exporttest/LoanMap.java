package com.cloud.web.exportexcel.exporttest;

/**
 * @author: liuheyong
 * @create: 2019-10-17
 * @description:
 */
public class LoanMap {

    private String town;//乡镇
    private String stationPersonName;//站长姓名
    private String customerName;//姓名
    private String idCard;//身份证号码
    private String telephone;//电话
    private String bankName;//银行名称
    private String LoanTime;//放款时间
    private String LoanMoney;//贷款金额
    private String LoanAllMoney;//代偿合计金额

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStationPersonName() {
        return stationPersonName;
    }

    public void setStationPersonName(String stationPersonName) {
        this.stationPersonName = stationPersonName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getLoanTime() {
        return LoanTime;
    }

    public void setLoanTime(String loanTime) {
        LoanTime = loanTime;
    }

    public String getLoanMoney() {
        return LoanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        LoanMoney = loanMoney;
    }

    public String getLoanAllMoney() {
        return LoanAllMoney;
    }

    public void setLoanAllMoney(String loanAllMoney) {
        LoanAllMoney = loanAllMoney;
    }
}
