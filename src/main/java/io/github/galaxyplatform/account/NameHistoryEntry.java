package io.github.galaxyplatform.account;

import lombok.Builder;

import java.time.Instant;

@Builder
public record NameHistoryEntry(
        String name,
        Instant seenAt
) {
}
