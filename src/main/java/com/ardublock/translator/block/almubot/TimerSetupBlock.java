package com.ardublock.translator.block.almubot;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TimerSetupBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public TimerSetupBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock subroutineName = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock ocrVal =  this.getRequiredTranslatorBlockAtSocket(1);

		String name = label;

		String setup = "TCCR2A = 0;\nTCCR2B = 0;\nTCNT2  = 0;\nOCR2A = " + ocrVal.toCode() + ";\nTCCR2A |= (1 << WGM21);\nTCCR2B |= (1 << CS21);\nTIMSK2 |= (1 << OCIE2A);";

		String command = "void " + subroutineName.getLabel() + "(void);\n\nISR(TIMER2_COMPA_vect){\n" + subroutineName.getLabel() + "();\n}\n";
		translator.addDefinitionCommand(command);

		translator.addSetupCommand(setup);

		return "";
	}

}
