package com.zlead.entity.vo;

import java.math.BigDecimal;

public class ShopIndexVo {
    private Long customerCount;
    private Long orderCount;
    private BigDecimal totalAmountWeek;
    private Long totalCountWeek;
    private Double finishRateWeek;
    private BigDecimal totalAmountMonth;
    private Long totalCountMonth;
    private Double finishRateMonth;
    private BigDecimal totalAmountSeason;
    private Long totalCountSeason;
    private Double finishRateSeason;

    public Long getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Long customerCount) {
        this.customerCount = customerCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getTotalAmountWeek() {
        return totalAmountWeek;
    }

    public void setTotalAmountWeek(BigDecimal totalAmountWeek) {
        this.totalAmountWeek = totalAmountWeek;
    }

    public Long getTotalCountWeek() {
        return totalCountWeek;
    }

    public void setTotalCountWeek(Long totalCountWeek) {
        this.totalCountWeek = totalCountWeek;
    }

    public Double getFinishRateWeek() {
        return finishRateWeek;
    }

    public void setFinishRateWeek(Double finishRateWeek) {
        this.finishRateWeek = finishRateWeek;
    }

    public BigDecimal getTotalAmountMonth() {
        return totalAmountMonth;
    }

    public void setTotalAmountMonth(BigDecimal totalAmountMonth) {
        this.totalAmountMonth = totalAmountMonth;
    }

    public Long getTotalCountMonth() {
        return totalCountMonth;
    }

    public void setTotalCountMonth(Long totalCountMonth) {
        this.totalCountMonth = totalCountMonth;
    }

    public Double getFinishRateMonth() {
        return finishRateMonth;
    }

    public void setFinishRateMonth(Double finishRateMonth) {
        this.finishRateMonth = finishRateMonth;
    }

    public BigDecimal getTotalAmountSeason() {
        return totalAmountSeason;
    }

    public void setTotalAmountSeason(BigDecimal totalAmountSeason) {
        this.totalAmountSeason = totalAmountSeason;
    }

    public Long getTotalCountSeason() {
        return totalCountSeason;
    }

    public void setTotalCountSeason(Long totalCountSeason) {
        this.totalCountSeason = totalCountSeason;
    }

    public Double getFinishRateSeason() {
        return finishRateSeason;
    }

    public void setFinishRateSeason(Double finishRateSeason) {
        this.finishRateSeason = finishRateSeason;
    }
}
