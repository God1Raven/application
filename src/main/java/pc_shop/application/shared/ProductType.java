package pc_shop.application.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductType {
    @JsonProperty("personal_computer")
    PERSONAL_COMPUTER,
    @JsonProperty("laptop")
    LAPTOP,
    @JsonProperty("monitor")
    MONITOR,
    @JsonProperty("hard_drive")
    HARD_DRIVE;
}
