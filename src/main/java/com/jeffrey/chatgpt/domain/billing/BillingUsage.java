package com.jeffrey.chatgpt.domain.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jeffrey You
 * @description Billing Usage
 */
@Data
public class BillingUsage {

    @JsonProperty("object")
    private String object;
    @JsonProperty("daily_costs")
    private List<DailyCost> dailyCosts;
    @JsonProperty("total_usage")
    private BigDecimal totalUsage;

}
