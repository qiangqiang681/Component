package com.eliv.component

import org.gradle.api.Project
import java.io.File
import java.util.*
import java.util.regex.Pattern

/**
 * Created by eliv on 19-6-20.
 */

fun File.loadProperties(name: String): String {
    val properties = Properties()
    properties.load(inputStream())
    return properties[name].toString()
}

fun File.setProperties(name: String, value: String) {
    val properties = Properties()
    properties.load(inputStream())
    properties.setProperty(name, value)
    properties.store(outputStream(), "update properties with configModule .")
}

fun Project.execCmd(cmd: String): Process {
    val process = Runtime.getRuntime().exec(cmd)
    return process
}

fun Process.readLines(consumer: ((String) -> Unit)) {
    inputStream.use {
        it.bufferedReader().forEachLine(consumer)
    }
}

fun Pattern.findString(str: String): String? {
    val matcher = matcher(str)
    if (matcher.find()) {
        return matcher.group(0).replace("'", "")
    }
    return null
}