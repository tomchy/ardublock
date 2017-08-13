package com.ardublock.translator.block.almubot;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class PIDSetupBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public PIDSetupBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{

		TranslatorBlock max = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock min = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock p = this.getRequiredTranslatorBlockAtSocket(2);
		TranslatorBlock i = this.getRequiredTranslatorBlockAtSocket(3);
		TranslatorBlock d = this.getRequiredTranslatorBlockAtSocket(4);

		translator.addHeaderFile("pid.h");
		String command = "PID " + label + "(" + p.toCode() + ", " + i.toCode() + ", " + d.toCode() + ", " + max.toCode() + ", " + min.toCode() + ");";
		translator.addDefinitionCommand(command);

		return "";
	}

}
