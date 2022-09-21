package ust.airwatcher.dto;

import lombok.Data;

import java.util.List;

@Data
public class AirQualityDto {

    private String city;
    private String state;
    private String country;
    private LocationDto location;
    private List<ForecastDto> forecasts;
    private CurrentDto currentDto;
    private HistoryDto historyDto;


}
