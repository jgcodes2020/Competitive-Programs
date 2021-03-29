// This Kotlin script is used in conjunction with CCCCompiler.kts.
// A run config is conveniently provided to help out.
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset

System.getProperties()!!.put("java.awt.headless", null);

fun showTextAreaDialog(): String {
	val area = JTextArea(20, 50)
	area.font = Font("Consolas", Font.PLAIN, 12);
	area.border = BorderFactory.createLineBorder(Color.BLACK);

	val pane = JOptionPane(area, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

	val dialog = pane.createDialog("Set Test Case");
	dialog.isVisible = true;
	dialog.dispose();

	val result = pane.value;
	if (result != JOptionPane.OK_OPTION) {
		println("cancelled, returning...")
		System.exit(0);
	}

	return area.text;
}

fun ansiCode(vararg codes: Int): String {
	return "\u001B[${codes.joinToString(separator = ";")}m"
}
println("Showing dialog...")
val txt = showTextAreaDialog();

println("Building process...")
val proc = ProcessBuilder("java", "Main.java")
	.redirectError(ProcessBuilder.Redirect.INHERIT)
	.redirectOutput(ProcessBuilder.Redirect.INHERIT)
	.directory(File("out/ccc").absoluteFile)
	.start()!!;
println("Process built, sending input");

val stream = PrintStream(proc.outputStream)

stream.use {
	it.print(txt);
}
proc.waitFor();

val code = if (proc.exitValue() == 0) 92 else 91;
println("${ansiCode(code)}Java exited with code ${proc.exitValue()}${ansiCode(0)}");