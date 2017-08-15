package com.ld.common.model;

import java.io.*;
import java.math.*;
import java.util.*;

public class FinanceReport implements Serializable
{
    private Long id;
    private Long subCompanyId;
    private Long bankId;
    private BigDecimal amount;
    private BigDecimal changeAmount;
    private Long status;
    private Date date;
    private String name;
    private Long branchCompanyId;
    private Long type;
    private Long incomeTypeId;
    private Long invalidCapitalTypeId;
    private String descs;
    private Long costTypeId;
    private String content;
    private String randomCode;
    private String filepath;
    private Long userId;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getSubCompanyId() {
        return this.subCompanyId;
    }
    
    public void setSubCompanyId(final Long subCompanyId) {
        this.subCompanyId = subCompanyId;
    }
    
    public Long getBankId() {
        return this.bankId;
    }
    
    public void setBankId(final Long bankId) {
        this.bankId = bankId;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Long getBranchCompanyId() {
        return this.branchCompanyId;
    }
    
    public void setBranchCompanyId(final Long branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }
    
    public Long getType() {
        return this.type;
    }
    
    public void setType(final Long type) {
        this.type = type;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(final Date date) {
        this.date = date;
    }
    
    public Long getInvalidCapitalTypeId() {
        return this.invalidCapitalTypeId;
    }
    
    public void setInvalidCapitalTypeId(final Long invalidCapitalTypeId) {
        this.invalidCapitalTypeId = invalidCapitalTypeId;
    }
    
    public String getDescs() {
        return this.descs;
    }
    
    public void setDescs(final String descs) {
        this.descs = descs;
    }
    
    public Long getIncomeTypeId() {
        return this.incomeTypeId;
    }
    
    public void setIncomeTypeId(final Long incomeTypeId) {
        this.incomeTypeId = incomeTypeId;
    }
    
    public Long getCostTypeId() {
        return this.costTypeId;
    }
    
    public void setCostTypeId(final Long costTypeId) {
        this.costTypeId = costTypeId;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public String getRandomCode() {
        return this.randomCode;
    }
    
    public void setRandomCode(final String randomCode) {
        this.randomCode = randomCode;
    }
    
    public String getFilepath() {
        return this.filepath;
    }
    
    public void setFilepath(final String filepath) {
        this.filepath = filepath;
    }
    
    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    
    public BigDecimal getChangeAmount() {
        return this.changeAmount;
    }
    
    public void setChangeAmount(final BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }
    
    public Long getStatus() {
        return this.status;
    }
    
    public void setStatus(final Long status) {
        this.status = status;
    }
}
