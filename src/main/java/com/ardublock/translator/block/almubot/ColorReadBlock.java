package com.ardublock.translator.block.almubot;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ColorReadBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public ColorReadBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock color = this.getRequiredTranslatorBlockAtSocket(0);

		translator.addDefinitionCommand("uint8_t " + label + "_colors[4];");

		String pre_command = label + ".read(" + label + "_colors, 4);\n";
		String command = label + "_colors" + color.toCode();

		return pre_command + codePrefix + command + codeSuffix;
	}

}
