package com.langtest.langtest.service;


import com.langtest.langtest.dto.ResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CheckTextServiceTest {

    @Autowired
    private CheckTextService checkTextService;

    @Test
    void shouldReturnCorrectAnswer(){
        String sample = "  dsfgsoliciu... !  Mauhis arcusu semihe . ir digil quisam impediec.  es macir quisua .  Nullam quir poral ac merul.   \n     Doneca punar de us nequl placeran sincdun. Neqed nil ulrices es hiraec qua rempl lacuse hisaec ers!";
        ResultDto resultDto = checkTextService.checkText(sample);
        assertEquals("INVALID",resultDto.getStatus());
        assertEquals(2,resultDto.getTypes().get(0).getCount());
        assertEquals(6,resultDto.getTypes().get(1).getCount());
        assertEquals(19,resultDto.getTypes().get(2).getCount());
        assertEquals(4,resultDto.getTypes().get(3).getCount());
        assertEquals(9,resultDto.getTypes().get(4).getCount());

        assertEquals(4,resultDto.getViolations().get(0).getCount());
        assertEquals(1,resultDto.getViolations().get(1).getCount());
        assertEquals(10,resultDto.getViolations().get(2).getCount());
        assertEquals(4,resultDto.getViolations().get(3).getCount());
        assertEquals(1,resultDto.getViolations().get(4).getCount());
        assertEquals(1,resultDto.getViolations().get(5).getCount());


    }
}
