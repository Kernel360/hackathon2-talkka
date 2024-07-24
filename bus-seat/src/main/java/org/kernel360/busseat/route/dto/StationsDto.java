package org.kernel360.busseat.route.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StationsDto {
	private Long stationId;
	private String stationName;
	private Integer stationSeq;
	private String turnYn;
	private String centerYn;
	private BigDecimal longitude;
	private BigDecimal latitude;
}
