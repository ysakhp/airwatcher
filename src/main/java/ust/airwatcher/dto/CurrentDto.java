package ust.airwatcher.dto;

import lombok.Data;

@Data
public class CurrentDto {
    private Weather weather;
    private Pollution pollution;
}
