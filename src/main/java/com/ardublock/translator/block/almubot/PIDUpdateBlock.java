package com.ardublock.translator.block.almubot;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class PIDUpdateBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public PIDUpdateBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock var = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock goal = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock current = this.getRequiredTranslatorBlockAtSocket(2);

		String command = label + ".update(" + current.toCode() + ", " + goal.toCode() + ", &" + var.toCode() + ");";
		
		return command;
	}

}
