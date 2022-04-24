package org.example

import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) = runBlocking  {
    if (args.size < 2) {
        println("usage: app input output")
        exitProcess(-1)
    }

    val input = args[0]
    val output = args[1]

    println(input)
    println(output)

    val unsafeDates = readFile(input)
    val safeDates = unsafeDates.map{"${processLine(it)}${System.lineSeparator()}" }
    writeFile(output, safeDates.toString())
}

fun readFile(fileName: String) : List<String> {
    return File(fileName).readLines()
}

fun writeFile(fileName: String, fileContent: String) {
    File(fileName).printWriter().use { out ->
        out.println(fileContent)
    }
}

fun processLine(line:String): String {
    val pattern = "DOB='[\\w \\-\\/]+'".toRegex()
    val match = pattern.find(line)

    val output = when (val potentialDOB = match?.value) {
        null -> line // no match in line
        else -> {
            val replace = obfuscateDate(potentialDOB)
            line.replace(potentialDOB, replace)
        }
    }
    return output
}

fun obfuscateDate(dobDate:String) :String {
    val pattern = "[\\d]{4}".toRegex()
    val match = pattern.find(dobDate)
    val year = match?.value
    return "DOB='X/X/$year'"
}

fun getResourceAsText(path: String): String =
    object {}.javaClass.getResource(path)?.readText() ?: ""

//Object (logfile) name: patients.log
