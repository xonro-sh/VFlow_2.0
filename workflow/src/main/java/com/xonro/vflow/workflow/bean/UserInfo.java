package com.xonro.vflow.workflow.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author louie
 * @date created in 2018-3-16 15:16
 */
public class UserInfo implements Serializable{
    /**
     * 用户id
     */
    @NotBlank(message = "userId can not be empty")
    private String userId;

    /**
     * 排序
     */
    private Integer orderIndex;

    /**
     * 电话
     */

    private String tel;

    /**
     * 工作地点
     */
    private String workPlace;

    /**
     * 手机号
     */
    @NotBlank(message = "mobile can't be empty")
    private String mobile;

    /**
     * 是否激活
     */
    @NotNull(message = "active can't be empty")
    private boolean active;

    /**
     * 职位
     */
    private String position;

    /**
     * 职位等级
     */
    private String positionLevel;

    /**
     * 入职日期
     */
    private String hiredDate;

    /**
     * 员工类型：全职、兼职、实习等
     */
    private String employeeType;

    /**
     * 员工状态：正式、使用
     */
    private String employeeState;

    /**
     * 身份证姓名
     */
    @NotBlank(message = "idCardName can't be empty")
    private String idCardName;

    /**
     * 身份证号
     */
    @NotBlank(message = "idCardNo can't be empty")
    private String idCardNo;

    /**
     * 出生日期
     */
    private String birthDate;

    /**
     * 性别
     */
    @NotBlank(message = "sex can't be empty")
    private String sex;

    /**
     * 民族
     */
    private String nation;

    /**
     * 身份证地址
     */
    private String idCardAddress;

    /**
     * 住址
     */
    private String address;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 婚姻状况
     */
    private String maritalStatus;

    /**
     * 学历
     */
    private String education;

    /**
     * 毕业院校
     */
    private String university;

    /**
     * 所学专业
     */
    private String major;

    /**
     * 毕业时间
     */
    private String graduationTime;

    /**
     * 银行卡号
     */
    private String bankCardNo;

    /**
     * 开户行
     */
    private String bank;

    /**
     * 身份证照片（正面）
     */
    private byte[] idCardPicture1;

    /**
     * 身份证照片（背面）
     */
    private byte[] idCardPicture2;

    /**
     * 备注
     */
    private String remark;

    /**
     * 租赁id
     */
    @NotBlank(message = "tenantId can't be empty")
    private String tenantId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel;
    }

    public String getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(String employeeState) {
        this.employeeState = employeeState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdCardAddress() {
        return idCardAddress;
    }

    public void setIdCardAddress(String idCardAddress) {
        this.idCardAddress = idCardAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public byte[] getIdCardPicture1() {
        return idCardPicture1;
    }

    public void setIdCardPicture1(byte[] idCardPicture1) {
        this.idCardPicture1 = idCardPicture1;
    }

    public byte[] getIdCardPicture2() {
        return idCardPicture2;
    }

    public void setIdCardPicture2(byte[] idCardPicture2) {
        this.idCardPicture2 = idCardPicture2;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", tel='" + tel + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", mobile='" + mobile + '\'' +
                ", active=" + active +
                ", position='" + position + '\'' +
                ", positionLevel='" + positionLevel + '\'' +
                ", hiredDate='" + hiredDate + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", employeeState='" + employeeState + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
