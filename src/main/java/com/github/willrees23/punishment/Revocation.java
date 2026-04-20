package com.github.willrees23.punishment;

import com.github.willrees23.account.Account;
import lombok.Builder;

import java.time.Instant;

@Builder
public record Revocation(
        Account revokedBy,
        Instant revokedAt,
        String reason
) {
}
