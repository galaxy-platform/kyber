package io.github.galaxyplatform.punishment;

import io.github.galaxyplatform.account.Account;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record Punishment(
        UUID id,
        Account target,
        Account issuer,
        PunishmentType type,
        String reason,
        Instant issuedAt,
        Instant expiresAt,
        Revocation revocation
) {

    public boolean isPermanent() {
        return expiresAt == null;
    }

    public boolean isExpired() {
        return expiresAt != null && Instant.now().isAfter(expiresAt);
    }

    public boolean isRevoked() {
        return revocation != null;
    }

    public boolean isActive() {
        return !isRevoked() && !isExpired();
    }

    public Punishment withRevocation(Revocation revocation) {
        return toBuilder().revocation(revocation).build();
    }
}
