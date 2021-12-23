package com.langtest.langtest.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResultDto {

    private String status;

    private List<ResultUnit> types;

    private List<ResultUnit> violations;

}
