package com.ardublock.translator.block.almubot;

import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.ConstBlock;

public class EncoderSelectPropertyAngleBlock extends ConstBlock
{

	public EncoderSelectPropertyAngleBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		this.setCode("getAngle()");
	}
}
