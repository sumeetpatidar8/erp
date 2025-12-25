package com.sumeet.erp.notifications;

import java.time.Instant;

public record AlertMessage(Long companyId, String type, String text, Instant at) {}