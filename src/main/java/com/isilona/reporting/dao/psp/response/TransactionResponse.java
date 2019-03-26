package com.isilona.reporting.dao.psp.response;

import com.isilona.reporting.dao.psp.CustomerInfo;
import com.isilona.reporting.dao.psp.Fx;
import com.isilona.reporting.dao.psp.Merchant;
import com.isilona.reporting.dao.psp.Transaction;

public class TransactionResponse {

    private CustomerInfo customerInfo;
    private Fx fx;
    private Transaction transaction;
    private Merchant merchant;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Fx getFx() {
        return fx;
    }

    public void setFx(Fx fx) {
        this.fx = fx;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
