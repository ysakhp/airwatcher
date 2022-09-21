package ust.airwatcher.dto;

import lombok.Data;

import java.util.List;

@Data
public class LocationDto {
    private String type;
    private List<Float> coordinates;
}
