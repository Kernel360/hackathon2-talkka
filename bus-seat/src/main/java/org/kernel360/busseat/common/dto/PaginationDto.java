package org.kernel360.busseat.common.dto;

import java.util.List;

import org.kernel360.busseat.route.dto.StationsDto;

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
public class PaginationDto<T> {
	private Pagination pagination;
	private List<StationsDto> data;
}
