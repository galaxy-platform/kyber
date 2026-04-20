package io.github.galaxyplatform.punishment;

import io.github.galaxyplatform.account.Account;
import lombok.Builder;

import java.time.Instant;

@Builder
public record Revocation(
        Account revokedBy,
        Instant revokedAt,
        String reason
) {
}
