package com.jeffrey.chatgpt.domain.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Jeffrey You
 * @description Daily Cost
 */
@Data
public class DailyCost {
    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("line_items")
    private List<LineItem> lineItems;
}
