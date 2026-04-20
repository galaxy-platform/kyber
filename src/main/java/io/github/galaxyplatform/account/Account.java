package io.github.galaxyplatform.account;

import lombok.Builder;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder(toBuilder = true)
public record Account(
        UUID uuid,
        String currentName,
        List<NameHistoryEntry> nameHistory,
        Instant firstSeen,
        Instant lastSeen,
        boolean online,
        Duration playtime,
        long loginCount,
        String currentAddress,
        List<AddressHistoryEntry> addressHistory
) {

    public Account {
        nameHistory = nameHistory == null ? List.of() : List.copyOf(nameHistory);
        addressHistory = addressHistory == null ? List.of() : List.copyOf(addressHistory);
        playtime = playtime == null ? Duration.ZERO : playtime;
    }

    public Account withName(String newName, Instant seenAt) {
        if (Objects.equals(currentName, newName)) {
            return this;
        }
        List<NameHistoryEntry> updated = new ArrayList<>(nameHistory);
        updated.add(new NameHistoryEntry(newName, seenAt));
        return toBuilder()
                .currentName(newName)
                .nameHistory(updated)
                .build();
    }

    public Account withAddress(String newAddress, Instant seenAt) {
        if (Objects.equals(currentAddress, newAddress)) {
            return this;
        }
        List<AddressHistoryEntry> updated = new ArrayList<>(addressHistory);
        updated.add(new AddressHistoryEntry(newAddress, seenAt));
        return toBuilder()
                .currentAddress(newAddress)
                .addressHistory(updated)
                .build();
    }

    public Account seenAt(Instant when) {
        return toBuilder()
                .firstSeen(firstSeen == null ? when : firstSeen)
                .lastSeen(when)
                .build();
    }

    public Account loggedIn(Instant when) {
        return toBuilder()
                .online(true)
                .loginCount(loginCount + 1)
                .firstSeen(firstSeen == null ? when : firstSeen)
                .lastSeen(when)
                .build();
    }

    public Account loggedOut(Instant when, Duration sessionLength) {
        return toBuilder()
                .online(false)
                .lastSeen(when)
                .playtime(playtime.plus(sessionLength == null ? Duration.ZERO : sessionLength))
                .build();
    }
}
