package com.eliv.component

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class ComponentTask : DefaultTask() {

    init {
        group = "build"
    }

    @TaskAction
    fun doTask() {
        configureRunModule()
    }

    private fun configureRunModule() {

        val file = File(project.projectDir, "gradle.properties")
        file.setProperties("isApplication", "true")

    }


}
