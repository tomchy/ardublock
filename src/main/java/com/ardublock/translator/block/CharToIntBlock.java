package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CharToIntBlock extends TranslatorBlock
{
	public CharToIntBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ret = "";
		//TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, codePrefix, codeSuffix);
		TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, "", "");
		if (translatorBlock != null)
		{
			ret = translatorBlock.toCode();
			ret = "((int) (" + ret + " - '0'))";
		}
		return codePrefix+ret+codeSuffix;
	}
}
