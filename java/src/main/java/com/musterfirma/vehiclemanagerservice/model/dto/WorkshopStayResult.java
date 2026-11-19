package com.musterfirma.vehiclemanagerservice.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class WorkshopStayResult {
    private String reason;

    private LocalDateTime startDate;

    private LocalDateTime plannedReturn;

    private LocalDateTime endDate;
}
