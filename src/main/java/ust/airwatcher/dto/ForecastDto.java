package ust.airwatcher.dto;

import lombok.Data;

@Data
public class ForecastDto {
    private String ts;
    private Integer aqius;
    private Integer aqicn;
    private Integer tp;
    private Integer tp_min;
    private Integer pr;
    private Integer hu;
    private Integer ws;
    private Integer wd;
    private String ic;
}
