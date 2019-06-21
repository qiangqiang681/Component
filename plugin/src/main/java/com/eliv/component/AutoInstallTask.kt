package com.eliv.component

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.regex.Pattern

/**
 * Created by eliv on 19-6-21.
 */
open class AutoInstallTask : DefaultTask() {

    val packagePattern = Pattern.compile("'(.*?)'")
    var packageName: String? = null
    var launchableActivity: String? = null

    init {
        group = "build"
    }

    @TaskAction
    fun doTask() {
        val localFile = File(project.rootProject.projectDir, "local.properties")
        val sdkPath = localFile.loadProperties("sdk.dir")

        val apkPath = File(project.buildDir, "/outputs/apk/debug/${project.name}-debug.apk").absolutePath
        println(apkPath)
        val buildToolsPath = File(sdkPath, "build-tools/").listFiles()[0].absolutePath
        println(buildToolsPath)
        val process = project.execCmd("$buildToolsPath/aapt dump badging $apkPath")

        process.readLines {
            if (it.startsWith("package")) {
                packageName = packagePattern.findString(it)
            }
            if (it.startsWith("launchable-activity")) {
                launchableActivity = packagePattern.findString(it)
            }
        }
        println(packageName)
        println(launchableActivity)

        val installProcess = project.execCmd("$sdkPath/platform-tools/adb install -r $apkPath")
        installProcess.readLines {
            if (it.equals("success", true)){
                println("===start launch activity===")
                project.execCmd("$sdkPath/platform-tools/adb shell am start -n $packageName/$launchableActivity")
            }
        }

        println("install end.")
    }
}