package com.github.willrees23.account;

import lombok.Builder;

import java.time.Instant;

@Builder
public record NameHistoryEntry(
        String name,
        Instant seenAt
) {
}
