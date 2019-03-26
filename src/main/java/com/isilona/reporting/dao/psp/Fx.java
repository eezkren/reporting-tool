package com.isilona.reporting.dao.psp;

public class Fx {

    private Merchant merchant;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    private static class Merchant {
        private Integer originalAmount;
        private String originalCurrency;

        public Integer getOriginalAmount() {
            return originalAmount;
        }

        public void setOriginalAmount(Integer originalAmount) {
            this.originalAmount = originalAmount;
        }

        public String getOriginalCurrency() {
            return originalCurrency;
        }

        public void setOriginalCurrency(String originalCurrency) {
            this.originalCurrency = originalCurrency;
        }
    }
}
