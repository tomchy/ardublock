package com.ardublock.translator.block.almubot;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ColorSetupBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public ColorSetupBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock OUT_pin = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock S2_pin  = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock S3_pin  = this.getRequiredTranslatorBlockAtSocket(2);

		String name = label;

		translator.addHeaderFile("gy31.h");

		translator.addSetupCommand("Serial.begin(9600);");

		String command = "GY31 " + label + "(" + OUT_pin.toCode() + ", " + S2_pin.toCode() + ", " + S3_pin.toCode() + ");";
		translator.addDefinitionCommand(command);

		String setup = "// Initialize sensor\nif (!" + label + ".begin())\n {\n Serial.println(\"Cannot initialize color sensor. Double check wires and rerun your program.\");\n}\n";
		setup += "\n// Check sensor connections\nif (!" + label + ".isChipConnected())\n{\nSerial.println(\"Color sensor disconnected. Double check wires and rerun your program.\");\n}\n";
		translator.addSetupCommand(setup);

		return "";
	}

}
