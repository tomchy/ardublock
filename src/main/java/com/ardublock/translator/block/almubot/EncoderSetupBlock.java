package com.ardublock.translator.block.almubot;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class EncoderSetupBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public EncoderSetupBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock TPR     = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock ENA_pin = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock ENB_pin = this.getRequiredTranslatorBlockAtSocket(2);

		String name = label;

		translator.addHeaderFile("encoder.h");

		translator.addSetupCommand("Serial.begin(9600);");

		String command = "Encoder ENC_" + label + "(" + ENA_pin.toCode() + ", " + ENB_pin.toCode() + ", " + TPR.toCode() + ");";
		translator.addDefinitionCommand(command);

		String setup = "ENC_" + label + ".begin();\n";
		setup += "ENC_" + label + ".resetState();\n";
		translator.addSetupCommand(setup);

		return "";
	}

}
