package io.github.galaxyplatform.account;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AddressHistoryEntry(
        String address,
        Instant seenAt
) {
}
