// This Kotlin script generates a CCC-ready Java file.
// A run config is conveniently provided to help out.
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.regex.Pattern
//timer
val startTime = System.currentTimeMillis()
//regexes
val pkgStatement = Pattern.compile("package [a-zA-Z\$_][\\w\$]*(?:\\.[a-z\$_][\\w\$]*)*;")
val clazzStatement = Pattern.compile("(?<=public class )[a-zA-Z\$_][\\w\$]*")

var notUsedPkg = true
var notUsedClazz = true
//files
val filePath = Paths.get(args[0]).toAbsolutePath()
val outputPath = Paths.get("out/ccc/Main.java").toAbsolutePath()
//safety checks
Files.createDirectories(outputPath.parent)
if (!Files.isRegularFile(outputPath)) {
	Files.createFile(outputPath)
}

if (!filePath.toString().toLowerCase(Locale.US).endsWith(".java")) {
	println("not java file, returning...");
	System.exit(0);
}

println("Compiling ${filePath.fileName} to CCC...")
println("------------------------------")

//reader and writer
val reader = BufferedReader(FileReader(filePath.toFile()))
val writer = BufferedWriter(FileWriter(outputPath.toFile(), false))

//clean up package and class declarations
fun cleanUp(line: String): String {
	val a: String
	if (notUsedPkg) {
		a = pkgStatement.matcher(line).replaceAll("")
		notUsedPkg = false
	}
	else a = line

	val b: String
	if (notUsedClazz) {
		b = clazzStatement.matcher(a).replaceAll("Main")
		notUsedClazz = false
	}
	else b = a
	return b
}
print("\u001b[36m")
//do the thing
reader.use {
	writer.use {
		while (reader.ready()) {
			val sanitized = cleanUp(reader.readLine())
			println(sanitized)
			writer.append(sanitized).append('\n')
		}
	}
}
println("\u001b[0m")
println("------------------------------")
println("${filePath.fileName} compiled to CCC in ${(System.currentTimeMillis() - startTime) / 1000.0} s")
