package ust.airwatcher.dto;

import lombok.Data;

@Data
public class Pollution {
    private String ts;
    private Integer aqius;
    private String mainus;
    private Integer aqicn;
    private String maincn;

    private PollutionDetails p2;
    private PollutionDetails p1;
    private PollutionDetails o3;
    private PollutionDetails n2;
    private PollutionDetails s2;
    private PollutionDetails co;

}
