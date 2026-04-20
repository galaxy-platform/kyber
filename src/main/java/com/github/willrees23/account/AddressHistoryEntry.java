package com.github.willrees23.account;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AddressHistoryEntry(
        String address,
        Instant seenAt
) {
}
