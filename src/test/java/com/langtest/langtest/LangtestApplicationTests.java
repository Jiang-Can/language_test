package com.langtest.langtest;

import com.langtest.langtest.content.Content;
import com.langtest.langtest.content.Sentence;
import com.langtest.langtest.content.Word;
import com.langtest.langtest.service.CheckTextService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class LangtestApplicationTests {

	//@Autowired
	ApplicationContext context;

	@Autowired
	CheckTextService checkTextService;

	@Test
	void contextLoads() {
	}

	@Test
	void test(){
		/*RulesChain wordRulesChain = (RulesChain) context.getBean(RulesChainType.WORD_RULES_CHAIN.getBeanName());
		RulesChain sentenceRulesChain = (RulesChain) context.getBean(RulesChainType.SENTENCE_RULES_CHAIN.getBeanName());
		RulesChain pRulesChain = (RulesChain) context.getBean(RulesChainType.PARAGRAPH_RULES_CHAIN.getBeanName());
		System.out.println(context.getClass());*/

		System.out.println("PARAGRAPH:");
		String[] split = "   Cufabiu maffas nonad in auguec finibu soliciu.  Mauhis arcusu semihe ir digil quisam impediec es macir quisua nullac. Nullam quir poral ac merul!   \n     Doneca punar de us nequl placeran sincdun. Neqed nil ulrices es hiraec qua rempl lacuse hisaec ers!".split("\n|\r\n|\r");
		Arrays.stream(split).forEach(str-> System.out.println("------->"+str.trim()+"<-------"));

		System.out.println("SENTENCE:");
		String[] sentences = "   Cufabiu maffas nonad   in auguec finibu soliciu   !!    sd   Mauhis arcusu semihe ir digil quisam impediec es macir quisua nullac...  . Nullam quir poral ac merul!".split("\\.|\\!");
		Arrays.stream(sentences).forEach(str-> System.out.println("------->"+str.trim()+"<-------"));

		System.out.println("WORD:");

		String[] s = "Cufabiu    maffas nonad in auguec   finibu soliciu".split("\\s+");
		Arrays.stream(s).forEach(str-> System.out.println("------->"+str.trim()+"<-------"));

		//ContentRoot c = new ContentRoot("  dsfgsoliciu... !  Mauhis arcusu semihe ir digil quisam impediec es .  macir quisua  . nullac. Nullam quir poral ac merul!   \n     Doneca punar de us nequl placeran sincdun. Neqed nil ulrices es hiraec qua rempl lacuse hisaec ers!");
		//c.buildSubContentNodes();

	}

	@Test
	public void test1(){
		Content word = new Word();
		Content sentence = new Sentence();

		Map<Class<? extends Content>,String> map = new HashMap<>();

		map.put(Word.class,"word");
		map.put(sentence.getClass(),"sentence");
		Content wo = new Word();
		System.out.println(map.get(wo.getClass()));
	}

	@Test
	public void test2(){
		System.out.println(checkTextService.checkText("  dsfgsoliciu... !  Mauhis arcusu semihe . ir digil quisam impediec.  es macir quisua .  Nullam quir poral ac merul.   \n     Doneca punar de us nequl placeran sincdun. Neqed nil ulrices es hiraec qua rempl lacuse hisaec ers!"));
	}
}
