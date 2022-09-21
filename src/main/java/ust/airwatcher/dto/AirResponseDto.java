package ust.airwatcher.dto;

import lombok.Data;

@Data
public class AirResponseDto<C> {

    private String status;
    private C data;
}
