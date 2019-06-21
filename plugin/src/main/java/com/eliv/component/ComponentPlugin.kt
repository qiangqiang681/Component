package com.eliv.component

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by eliv on 19-6-20.
 */
class ComponentPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val componentTask = project.tasks.create("runModule", ComponentTask::class.java)
        val autoInstallTask = project.tasks.create("autoInstallTask", AutoInstallTask::class.java)
        project.afterEvaluate {
            componentTask.finalizedBy("assembleDebug").finalizedBy(autoInstallTask)
        }
    }
}