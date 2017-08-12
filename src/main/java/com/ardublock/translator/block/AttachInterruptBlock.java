package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AttachInterruptBlock extends TranslatorBlock
{

	public AttachInterruptBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ret;
		TranslatorBlock pin = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock trigger = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock pullup = this.getRequiredTranslatorBlockAtSocket(2);

		String setupCode;
		String pullupName = pullup.toCode();


		if (pullupName.equals("LOW")) {
			setupCode = "pinMode( " + pin.toCode() + " , INPUT);";
		} else {
			setupCode = "pinMode( " + pin.toCode() + " , INPUT_PULLUP);";
		}
		translator.addSetupCommand(setupCode);
		int interruptnumber = Integer.parseInt(pin.toCode()) - 2;
		translator.addSetupCommand("attachInterrupt(" + Integer.toString(interruptnumber) + ", _INTERRUPT_P" + pin.toCode() + ", " + trigger.toCode() + ");");


		//String
		ret = "void _INTERRUPT_P" + pin.toCode() + "()\n{\n";
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(3);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		ret = ret + "}\n\n";
		translator.addDefinitionCommand(ret);

		return "";
	}
}
