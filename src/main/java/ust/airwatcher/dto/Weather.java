package ust.airwatcher.dto;

import lombok.Data;

@Data
public class Weather {
    private String ts;
    private Integer tp;
    private Integer pr;
    private Integer hu;
    private Integer ws;
    private Integer wd;
    private String ic;
}
