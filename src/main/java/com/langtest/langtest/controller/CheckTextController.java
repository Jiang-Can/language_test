package com.langtest.langtest.controller;


import com.langtest.langtest.dto.ResultDto;
import com.langtest.langtest.dto.TextPostDto;
import com.langtest.langtest.service.CheckTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CheckTextController {

    private final CheckTextService checkTextService;

    @PostMapping("/text")
    public ResponseEntity<ResultDto> processingText(@Valid @RequestBody TextPostDto textPostDto){
        return ResponseEntity.ok(checkTextService.checkText(textPostDto.getText()));
    }


}
