package com.example.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {
  private final int defaultAmount;
  private final int transferCommission;

  public AccountProperties(
    @Value("${account.default-amount}") int defaultAmount,
    @Value("${account.transfer-commission}") int transferCommission ) {
    this.defaultAmount = defaultAmount;
    this.transferCommission = transferCommission;
  }

  public int getDefaultAmount() {
    return defaultAmount;
  }

  public int getTransferCommission() {
    return transferCommission;
  } 

}
