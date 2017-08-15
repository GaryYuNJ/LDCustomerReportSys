package com.ld.saleseport.bo;

import com.ld.common.model.*;
import java.io.*;
import com.ld.common.utils.*;

public class SalesReportBo extends House implements Serializable
{
    private String userId;
    private String projectName;
    private String projectPhase;
    private String buildName;
    private String erpName;
    private String houseTypeId;
    private String houseTypeName;
    private String houseConsultant;
    private String customerName;
    private String houseBusinessTypeId;
    private String houseBusinessTypeName;
    private String houseStatusName;
    private String cityCompanyId;
    private String isLargeAmount;
    private String pre_unit_price;
    private String field;
    private String tableName;
    private String dateStr;
    private String whereStr;
    private String house_id;
    private String type_id;
    private String building_id;
    private String status_id;
    private String sale_type_id;
    private String business_type_id;
    private String business_sub_type_id;
    private String house_code;
    private String house_name;
    private String erp_code;
    private String erp_name;
    private String erp_sale_code;
    private String customer_code;
    private String customer_name;
    private String customer_mobile;
    private String customer_number;
    private String pre_size;
    private String real_size;
    private String is_carry_forward;
    private String carry_forward_income;
    private String carry_forward_cost_unit_price;
    private String deliver_house_invoice_amount;
    private String deliver_house_invoice_code;
    private String is_change_name;
    private String dimission_payoff_time;
    private String dimission_remark;
    private String remark;
    private String extra_dimission_ratio;
    private String extra_dimission_ratio_remark;
    private String commission_calcu_type_id;
    private String overdue_remark;
    private String overdue_time;
    private String create_time;
    private String update_time;
    private String update_user;
    private String update_user_name;
    private String is_large_amount;
    private String project_erp_code;
    private String project_name;
    private String city_company_id;
    private String city_company_name;
    private String project_company_id;
    private String project_company_name;
    private String sale_start_time;
    private String saled_ratio;
    private String accepted_ratio;
    private String building_erp_code;
    private String building_name;
    private String land;
    private String project_id;
    private String project_phase;
    private String fund_ready_time;
    private String pos_amount;
    private String cash_amount;
    private String bank_bill_amount;
    private String bank_transfer_amount;
    private String pay_time;
    private String pay_type;
    private String bank_name;
    private String receipt_company;
    private String bank_bill_type;
    private String convert_house_amount;
    private String refund_amount;
    private String remain_amount;
    private String receipt_amount;
    private String status;
    private String refund_check_number;
    private String contract_code;
    private String unit_price;
    private String total_price;
    private String buy_method_id;
    private String sign_date;
    private String verification_date;
    private String late_fee_ratio;
    private String contract_id;
    private String mortgage_type_id;
    private String downpayments_ratio;
    private String downpayments_amount;
    private String business_loan_amount;
    private String public_fund_loan_amount;
    private String loan_bank;
    private String mortgage_department;
    private String contract_money_id;
    private String pay_amount;
    private String bank_bill_number;
    private String receipt_name;
    private String is_late_fee_calculate;
    private String is_overdue;
    private String late_fee;
    private String overdue_reason_id;
    private String erp_create_time;
    private String erp_paid_amount;
    private String erp_late_fee_ratio;
    private String erp_late_fee;
    private String is_dimission_calculate;
    private String receivables_time;
    private String overdue_extend_remark;
    private String is_recover_available;
    private String expect_recover_date;
    private String pay_detail_id;
    private String money_type_id;
    private String money_type_name;
    private String type;
    private String code;
    private String old_code;
    private String is_paid_added_tax;
    private String is_paid_sales_tax;
    private String amount;
    private String isvalid;
    private String invoice_tax_rate;
    private String invoice_tax;
    private String invoice_time;
    private String refund_time;
    private String columnZh;
    private String columnEn;
    private String fileName;
    private String reportType;
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public String getProjectName() {
        return this.projectName;
    }
    
    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }
    
    public String getProjectPhase() {
        return this.projectPhase;
    }
    
    public void setProjectPhase(final String projectPhase) {
        this.projectPhase = projectPhase;
    }
    
    public String getBuildName() {
        return this.buildName;
    }
    
    public void setBuildName(final String buildName) {
        this.buildName = buildName;
    }
    
    public String getErpName() {
        return this.erpName;
    }
    
    public void setErpName(final String erpName) {
        this.erpName = erpName;
    }
    
    public String getHouseTypeId() {
        return this.houseTypeId;
    }
    
    public void setHouseTypeId(final String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }
    
    public String getHouseTypeName() {
        return this.houseTypeName;
    }
    
    public void setHouseTypeName(final String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }
    
    public String getHouseConsultant() {
        return this.houseConsultant;
    }
    
    public void setHouseConsultant(final String houseConsultant) {
        this.houseConsultant = houseConsultant;
    }
    
    public String getCustomerName() {
        return this.customerName;
    }
    
    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }
    
    public String getHouseBusinessTypeId() {
        return this.houseBusinessTypeId;
    }
    
    public void setHouseBusinessTypeId(final String houseBusinessTypeId) {
        this.houseBusinessTypeId = houseBusinessTypeId;
    }
    
    public String getHouseBusinessTypeName() {
        return this.houseBusinessTypeName;
    }
    
    public void setHouseBusinessTypeName(final String houseBusinessTypeName) {
        this.houseBusinessTypeName = houseBusinessTypeName;
    }
    
    public String getHouseStatusName() {
        return this.houseStatusName;
    }
    
    public void setHouseStatusName(final String houseStatusName) {
        this.houseStatusName = houseStatusName;
    }
    
    public String getCityCompanyId() {
        return this.cityCompanyId;
    }
    
    public void setCityCompanyId(final String cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }
    
    public String getIsLargeAmount() {
        return this.isLargeAmount;
    }
    
    public void setIsLargeAmount(final String isLargeAmount) {
        this.isLargeAmount = isLargeAmount;
    }
    
    public String getField() {
        return this.field;
    }
    
    public void setField(final String field) {
        this.field = field;
    }
    
    public String getTableName() {
        return this.tableName;
    }
    
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }
    
    public String getType_id() {
        return this.type_id;
    }
    
    public void setType_id(final String type_id) {
        this.type_id = type_id;
    }
    
    public String getBuilding_id() {
        return this.building_id;
    }
    
    public void setBuilding_id(final String building_id) {
        this.building_id = building_id;
    }
    
    public String getStatus_id() {
        return this.status_id;
    }
    
    public void setStatus_id(final String status_id) {
        this.status_id = status_id;
    }
    
    public String getSale_type_id() {
        return this.sale_type_id;
    }
    
    public void setSale_type_id(final String sale_type_id) {
        this.sale_type_id = sale_type_id;
    }
    
    public String getBusiness_type_id() {
        return this.business_type_id;
    }
    
    public void setBusiness_type_id(final String business_type_id) {
        this.business_type_id = business_type_id;
    }
    
    public String getBusiness_sub_type_id() {
        return this.business_sub_type_id;
    }
    
    public void setBusiness_sub_type_id(final String business_sub_type_id) {
        this.business_sub_type_id = business_sub_type_id;
    }
    
    public String getErp_code() {
        return this.erp_code;
    }
    
    public void setErp_code(final String erp_code) {
        this.erp_code = erp_code;
    }
    
    public String getErp_name() {
        return this.erp_name;
    }
    
    public void setErp_name(final String erp_name) {
        this.erp_name = erp_name;
    }
    
    public String getErp_sale_code() {
        return this.erp_sale_code;
    }
    
    public void setErp_sale_code(final String erp_sale_code) {
        this.erp_sale_code = erp_sale_code;
    }
    
    public String getCustomer_code() {
        return this.customer_code;
    }
    
    public void setCustomer_code(final String customer_code) {
        this.customer_code = customer_code;
    }
    
    public String getCustomer_name() {
        return this.customer_name;
    }
    
    public void setCustomer_name(final String customer_name) {
        this.customer_name = customer_name;
    }
    
    public String getCustomer_mobile() {
        return this.customer_mobile;
    }
    
    public void setCustomer_mobile(final String customer_mobile) {
        this.customer_mobile = customer_mobile;
    }
    
    public String getCustomer_number() {
        return this.customer_number;
    }
    
    public void setCustomer_number(final String customer_number) {
        this.customer_number = customer_number;
    }
    
    public String getPre_size() {
        return this.pre_size;
    }
    
    public void setPre_size(final String pre_size) {
        this.pre_size = pre_size;
    }
    
    public String getReal_size() {
        return this.real_size;
    }
    
    public void setReal_size(final String real_size) {
        this.real_size = real_size;
    }
    
    public String getIs_carry_forward() {
        return this.is_carry_forward;
    }
    
    public void setIs_carry_forward(final String is_carry_forward) {
        this.is_carry_forward = is_carry_forward;
    }
    
    public String getCarry_forward_income() {
        return this.carry_forward_income;
    }
    
    public void setCarry_forward_income(final String carry_forward_income) {
        this.carry_forward_income = carry_forward_income;
    }
    
    public String getCarry_forward_cost_unit_price() {
        return this.carry_forward_cost_unit_price;
    }
    
    public void setCarry_forward_cost_unit_price(final String carry_forward_cost_unit_price) {
        this.carry_forward_cost_unit_price = carry_forward_cost_unit_price;
    }
    
    public String getDeliver_house_invoice_amount() {
        return this.deliver_house_invoice_amount;
    }
    
    public void setDeliver_house_invoice_amount(final String deliver_house_invoice_amount) {
        this.deliver_house_invoice_amount = deliver_house_invoice_amount;
    }
    
    public String getDeliver_house_invoice_code() {
        return this.deliver_house_invoice_code;
    }
    
    public void setDeliver_house_invoice_code(final String deliver_house_invoice_code) {
        this.deliver_house_invoice_code = deliver_house_invoice_code;
    }
    
    public String getIs_change_name() {
        return this.is_change_name;
    }
    
    public void setIs_change_name(final String is_change_name) {
        this.is_change_name = is_change_name;
    }
    
    public String getDimission_payoff_time() {
        return this.dimission_payoff_time;
    }
    
    public void setDimission_payoff_time(final String dimission_payoff_time) {
        this.dimission_payoff_time = dimission_payoff_time;
    }
    
    public String getDimission_remark() {
        return this.dimission_remark;
    }
    
    public void setDimission_remark(final String dimission_remark) {
        this.dimission_remark = dimission_remark;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public String getExtra_dimission_ratio() {
        return this.extra_dimission_ratio;
    }
    
    public void setExtra_dimission_ratio(final String extra_dimission_ratio) {
        this.extra_dimission_ratio = extra_dimission_ratio;
    }
    
    public String getExtra_dimission_ratio_remark() {
        return this.extra_dimission_ratio_remark;
    }
    
    public void setExtra_dimission_ratio_remark(final String extra_dimission_ratio_remark) {
        this.extra_dimission_ratio_remark = extra_dimission_ratio_remark;
    }
    
    public String getCommission_calcu_type_id() {
        return this.commission_calcu_type_id;
    }
    
    public void setCommission_calcu_type_id(final String commission_calcu_type_id) {
        this.commission_calcu_type_id = commission_calcu_type_id;
    }
    
    public String getOverdue_remark() {
        return this.overdue_remark;
    }
    
    public void setOverdue_remark(final String overdue_remark) {
        this.overdue_remark = overdue_remark;
    }
    
    public String getCreate_time() {
        return this.create_time;
    }
    
    public void setCreate_time(final String create_time) {
        this.create_time = create_time;
    }
    
    public String getUpdate_time() {
        return this.update_time;
    }
    
    public void setUpdate_time(final String update_time) {
        this.update_time = (StringUtils.isNotBlank(update_time) ? DateUtil.stringDateFormat(update_time, "yyyy-MM-dd") : "");
    }
    
    public String getUpdate_user() {
        return this.update_user;
    }
    
    public void setUpdate_user(final String update_user) {
        this.update_user = update_user;
    }
    
    public String getIs_large_amount() {
        return this.is_large_amount;
    }
    
    public void setIs_large_amount(final String is_large_amount) {
        this.is_large_amount = is_large_amount;
    }
    
    public String getProject_name() {
        return this.project_name;
    }
    
    public void setProject_name(final String project_name) {
        this.project_name = project_name;
    }
    
    public String getCity_company_id() {
        return this.city_company_id;
    }
    
    public void setCity_company_id(final String city_company_id) {
        this.city_company_id = city_company_id;
    }
    
    public String getProject_company_id() {
        return this.project_company_id;
    }
    
    public void setProject_company_id(final String project_company_id) {
        this.project_company_id = project_company_id;
    }
    
    public String getSale_start_time() {
        return this.sale_start_time;
    }
    
    public void setSale_start_time(final String sale_start_time) {
        this.sale_start_time = (StringUtils.isNotBlank(sale_start_time) ? DateUtil.stringDateFormat(sale_start_time, "yyyy-MM-dd") : "");
    }
    
    public String getSaled_ratio() {
        return this.saled_ratio;
    }
    
    public void setSaled_ratio(final String saled_ratio) {
        this.saled_ratio = saled_ratio;
    }
    
    public String getAccepted_ratio() {
        return this.accepted_ratio;
    }
    
    public void setAccepted_ratio(final String accepted_ratio) {
        this.accepted_ratio = accepted_ratio;
    }
    
    public String getLand() {
        return this.land;
    }
    
    public void setLand(final String land) {
        this.land = land;
    }
    
    public String getProject_id() {
        return this.project_id;
    }
    
    public void setProject_id(final String project_id) {
        this.project_id = project_id;
    }
    
    public String getProject_phase() {
        return this.project_phase;
    }
    
    public void setProject_phase(final String project_phase) {
        this.project_phase = project_phase;
    }
    
    public String getFund_ready_time() {
        return this.fund_ready_time;
    }
    
    public void setFund_ready_time(final String fund_ready_time) {
        this.fund_ready_time = fund_ready_time;
    }
    
    public String getDateStr() {
        return this.dateStr;
    }
    
    public void setDateStr(final String dateStr) {
        this.dateStr = dateStr;
    }
    
    public String getPay_time() {
        return this.pay_time;
    }
    
    public void setPay_time(final String pay_time) {
        this.pay_time = (StringUtils.isNotBlank(pay_time) ? DateUtil.stringDateFormat(pay_time, "yyyy-MM-dd") : "");
    }
    
    public String getHouse_id() {
        return this.house_id;
    }
    
    public void setHouse_id(final String house_id) {
        this.house_id = house_id;
    }
    
    public String getPos_amount() {
        return this.pos_amount;
    }
    
    public void setPos_amount(final String pos_amount) {
        this.pos_amount = pos_amount;
    }
    
    public String getCash_amount() {
        return this.cash_amount;
    }
    
    public void setCash_amount(final String cash_amount) {
        this.cash_amount = cash_amount;
    }
    
    public String getBank_bill_amount() {
        return this.bank_bill_amount;
    }
    
    public void setBank_bill_amount(final String bank_bill_amount) {
        this.bank_bill_amount = bank_bill_amount;
    }
    
    public String getBank_transfer_amount() {
        return this.bank_transfer_amount;
    }
    
    public void setBank_transfer_amount(final String bank_transfer_amount) {
        this.bank_transfer_amount = bank_transfer_amount;
    }
    
    public String getPay_type() {
        return this.pay_type;
    }
    
    public void setPay_type(final String pay_type) {
        this.pay_type = pay_type;
    }
    
    public String getBank_bill_type() {
        return this.bank_bill_type;
    }
    
    public void setBank_bill_type(final String bank_bill_type) {
        this.bank_bill_type = bank_bill_type;
    }
    
    public String getConvert_house_amount() {
        return this.convert_house_amount;
    }
    
    public void setConvert_house_amount(final String convert_house_amount) {
        this.convert_house_amount = convert_house_amount;
    }
    
    public String getRefund_amount() {
        return this.refund_amount;
    }
    
    public void setRefund_amount(final String refund_amount) {
        this.refund_amount = refund_amount;
    }
    
    public String getRemain_amount() {
        return this.remain_amount;
    }
    
    public void setRemain_amount(final String remain_amount) {
        this.remain_amount = remain_amount;
    }
    
    public String getReceipt_amount() {
        return this.receipt_amount;
    }
    
    public void setReceipt_amount(final String receipt_amount) {
        this.receipt_amount = receipt_amount;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public String getBank_name() {
        return this.bank_name;
    }
    
    public void setBank_name(final String bank_name) {
        this.bank_name = bank_name;
    }
    
    public String getReceipt_company() {
        return this.receipt_company;
    }
    
    public void setReceipt_company(final String receipt_company) {
        this.receipt_company = receipt_company;
    }
    
    public String getRefund_check_number() {
        return this.refund_check_number;
    }
    
    public void setRefund_check_number(final String refund_check_number) {
        this.refund_check_number = refund_check_number;
    }
    
    public String getContract_code() {
        return this.contract_code;
    }
    
    public void setContract_code(final String contract_code) {
        this.contract_code = contract_code;
    }
    
    public String getUnit_price() {
        return this.unit_price;
    }
    
    public void setUnit_price(final String unit_price) {
        this.unit_price = unit_price;
    }
    
    public String getBuy_method_id() {
        return this.buy_method_id;
    }
    
    public void setBuy_method_id(final String buy_method_id) {
        this.buy_method_id = buy_method_id;
    }
    
    public String getSign_date() {
        return this.sign_date;
    }
    
    public void setSign_date(final String sign_date) {
        this.sign_date = sign_date;
    }
    
    public String getVerification_date() {
        return this.verification_date;
    }
    
    public void setVerification_date(final String verification_date) {
        this.verification_date = verification_date;
    }
    
    public String getLate_fee_ratio() {
        return this.late_fee_ratio;
    }
    
    public void setLate_fee_ratio(final String late_fee_ratio) {
        this.late_fee_ratio = late_fee_ratio;
    }
    
    public String getWhereStr() {
        return this.whereStr;
    }
    
    public void setWhereStr(final String whereStr) {
        this.whereStr = whereStr;
    }
    
    public String getCity_company_name() {
        return this.city_company_name;
    }
    
    public void setCity_company_name(final String city_company_name) {
        this.city_company_name = city_company_name;
    }
    
    public String getProject_company_name() {
        return this.project_company_name;
    }
    
    public void setProject_company_name(final String project_company_name) {
        this.project_company_name = project_company_name;
    }
    
    public String getPay_detail_id() {
        return this.pay_detail_id;
    }
    
    public void setPay_detail_id(final String pay_detail_id) {
        this.pay_detail_id = pay_detail_id;
    }
    
    public String getMoney_type_id() {
        return this.money_type_id;
    }
    
    public void setMoney_type_id(final String money_type_id) {
        this.money_type_id = money_type_id;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public String getOld_code() {
        return this.old_code;
    }
    
    public void setOld_code(final String old_code) {
        this.old_code = old_code;
    }
    
    public String getIs_paid_added_tax() {
        return this.is_paid_added_tax;
    }
    
    public void setIs_paid_added_tax(final String is_paid_added_tax) {
        this.is_paid_added_tax = is_paid_added_tax;
    }
    
    public String getIs_paid_sales_tax() {
        return this.is_paid_sales_tax;
    }
    
    public void setIs_paid_sales_tax(final String is_paid_sales_tax) {
        this.is_paid_sales_tax = is_paid_sales_tax;
    }
    
    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(final String amount) {
        this.amount = amount;
    }
    
    public String getIsvalid() {
        return this.isvalid;
    }
    
    public void setIsvalid(final String isvalid) {
        this.isvalid = isvalid;
    }
    
    public String getInvoice_tax_rate() {
        return this.invoice_tax_rate;
    }
    
    public void setInvoice_tax_rate(final String invoice_tax_rate) {
        this.invoice_tax_rate = invoice_tax_rate;
    }
    
    public String getInvoice_tax() {
        return this.invoice_tax;
    }
    
    public void setInvoice_tax(final String invoice_tax) {
        this.invoice_tax = invoice_tax;
    }
    
    public String getInvoice_time() {
        return this.invoice_time;
    }
    
    public void setInvoice_time(final String invoice_time) {
        this.invoice_time = (StringUtils.isNotBlank(invoice_time) ? DateUtil.stringDateFormat(invoice_time, "yyyy-MM-dd") : "");
    }
    
    public String getContract_money_id() {
        return this.contract_money_id;
    }
    
    public void setContract_money_id(final String contract_money_id) {
        this.contract_money_id = contract_money_id;
    }
    
    public String getPay_amount() {
        return this.pay_amount;
    }
    
    public void setPay_amount(final String pay_amount) {
        this.pay_amount = pay_amount;
    }
    
    public String getBank_bill_number() {
        return this.bank_bill_number;
    }
    
    public void setBank_bill_number(final String bank_bill_number) {
        this.bank_bill_number = bank_bill_number;
    }
    
    public String getReceipt_name() {
        return this.receipt_name;
    }
    
    public void setReceipt_name(final String receipt_name) {
        this.receipt_name = receipt_name;
    }
    
    public String getIs_late_fee_calculate() {
        return this.is_late_fee_calculate;
    }
    
    public void setIs_late_fee_calculate(final String is_late_fee_calculate) {
        this.is_late_fee_calculate = is_late_fee_calculate;
    }
    
    public String getIs_overdue() {
        return this.is_overdue;
    }
    
    public void setIs_overdue(final String is_overdue) {
        this.is_overdue = is_overdue;
    }
    
    public String getLate_fee() {
        return this.late_fee;
    }
    
    public void setLate_fee(final String late_fee) {
        this.late_fee = late_fee;
    }
    
    public String getOverdue_reason_id() {
        return this.overdue_reason_id;
    }
    
    public void setOverdue_reason_id(final String overdue_reason_id) {
        this.overdue_reason_id = overdue_reason_id;
    }
    
    public String getErp_create_time() {
        return this.erp_create_time;
    }
    
    public void setErp_create_time(final String erp_create_time) {
        this.erp_create_time = erp_create_time;
    }
    
    public String getErp_paid_amount() {
        return this.erp_paid_amount;
    }
    
    public void setErp_paid_amount(final String erp_paid_amount) {
        this.erp_paid_amount = erp_paid_amount;
    }
    
    public String getErp_late_fee_ratio() {
        return this.erp_late_fee_ratio;
    }
    
    public void setErp_late_fee_ratio(final String erp_late_fee_ratio) {
        this.erp_late_fee_ratio = erp_late_fee_ratio;
    }
    
    public String getErp_late_fee() {
        return this.erp_late_fee;
    }
    
    public void setErp_late_fee(final String erp_late_fee) {
        this.erp_late_fee = erp_late_fee;
    }
    
    public String getIs_dimission_calculate() {
        return this.is_dimission_calculate;
    }
    
    public void setIs_dimission_calculate(final String is_dimission_calculate) {
        this.is_dimission_calculate = is_dimission_calculate;
    }
    
    public String getReceivables_time() {
        return this.receivables_time;
    }
    
    public void setReceivables_time(final String receivables_time) {
        this.receivables_time = receivables_time;
    }
    
    public String getOverdue_extend_remark() {
        return this.overdue_extend_remark;
    }
    
    public void setOverdue_extend_remark(final String overdue_extend_remark) {
        this.overdue_extend_remark = overdue_extend_remark;
    }
    
    public String getIs_recover_available() {
        return this.is_recover_available;
    }
    
    public void setIs_recover_available(final String is_recover_available) {
        this.is_recover_available = is_recover_available;
    }
    
    public String getExpect_recover_date() {
        return this.expect_recover_date;
    }
    
    public void setExpect_recover_date(final String expect_recover_date) {
        this.expect_recover_date = expect_recover_date;
    }
    
    public String getContract_id() {
        return this.contract_id;
    }
    
    public void setContract_id(final String contract_id) {
        this.contract_id = contract_id;
    }
    
    public String getMortgage_type_id() {
        return this.mortgage_type_id;
    }
    
    public void setMortgage_type_id(final String mortgage_type_id) {
        this.mortgage_type_id = mortgage_type_id;
    }
    
    public String getDownpayments_ratio() {
        return this.downpayments_ratio;
    }
    
    public void setDownpayments_ratio(final String downpayments_ratio) {
        this.downpayments_ratio = downpayments_ratio;
    }
    
    public String getDownpayments_amount() {
        return this.downpayments_amount;
    }
    
    public void setDownpayments_amount(final String downpayments_amount) {
        this.downpayments_amount = downpayments_amount;
    }
    
    public String getBusiness_loan_amount() {
        return this.business_loan_amount;
    }
    
    public void setBusiness_loan_amount(final String business_loan_amount) {
        this.business_loan_amount = business_loan_amount;
    }
    
    public String getPublic_fund_loan_amount() {
        return this.public_fund_loan_amount;
    }
    
    public void setPublic_fund_loan_amount(final String public_fund_loan_amount) {
        this.public_fund_loan_amount = public_fund_loan_amount;
    }
    
    public String getLoan_bank() {
        return this.loan_bank;
    }
    
    public void setLoan_bank(final String loan_bank) {
        this.loan_bank = loan_bank;
    }
    
    public String getMortgage_department() {
        return this.mortgage_department;
    }
    
    public void setMortgage_department(final String mortgage_department) {
        this.mortgage_department = mortgage_department;
    }
    
    public String getMoney_type_name() {
        return this.money_type_name;
    }
    
    public void setMoney_type_name(final String money_type_name) {
        this.money_type_name = money_type_name;
    }
    
    public String getUpdate_user_name() {
        return this.update_user_name;
    }
    
    public void setUpdate_user_name(final String update_user_name) {
        this.update_user_name = update_user_name;
    }
    
    public String getProject_erp_code() {
        return this.project_erp_code;
    }
    
    public void setProject_erp_code(final String project_erp_code) {
        this.project_erp_code = project_erp_code;
    }
    
    public String getBuilding_erp_code() {
        return this.building_erp_code;
    }
    
    public void setBuilding_erp_code(final String building_erp_code) {
        this.building_erp_code = building_erp_code;
    }
    
    public String getTotal_price() {
        return this.total_price;
    }
    
    public void setTotal_price(final String total_price) {
        this.total_price = total_price;
    }
    
    public String getHouse_code() {
        return this.house_code;
    }
    
    public void setHouse_code(final String house_code) {
        this.house_code = house_code;
    }
    
    public String getHouse_name() {
        return this.house_name;
    }
    
    public void setHouse_name(final String house_name) {
        this.house_name = house_name;
    }
    
    public String getPre_unit_price() {
        return this.pre_unit_price;
    }
    
    public void setPre_unit_price(final String pre_unit_price) {
        this.pre_unit_price = pre_unit_price;
    }
    
    public String getRefund_time() {
        return this.refund_time;
    }
    
    public void setRefund_time(final String refund_time) {
        this.refund_time = refund_time;
    }
    
    public String getBuilding_name() {
        return this.building_name;
    }
    
    public void setBuilding_name(final String building_name) {
        this.building_name = building_name;
    }
    
    public String getColumnZh() {
        return this.columnZh;
    }
    
    public void setColumnZh(final String columnZh) {
        this.columnZh = columnZh;
    }
    
    public String getColumnEn() {
        return this.columnEn;
    }
    
    public void setColumnEn(final String columnEn) {
        this.columnEn = columnEn;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public String getReportType() {
        return this.reportType;
    }
    
    public void setReportType(final String reportType) {
        this.reportType = reportType;
    }
    
    public String getOverdue_time() {
        return this.overdue_time;
    }
    
    public void setOverdue_time(final String overdue_time) {
        this.overdue_time = overdue_time;
    }
}
