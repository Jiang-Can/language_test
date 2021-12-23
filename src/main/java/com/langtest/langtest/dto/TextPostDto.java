package com.langtest.langtest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TextPostDto {

    @NotBlank
    private String text;
}
