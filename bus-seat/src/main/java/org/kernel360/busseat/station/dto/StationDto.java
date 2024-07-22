package org.kernel360.busseat.station.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationDto {
	private Long stationId;

	private String stationName;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String stationType;
}
