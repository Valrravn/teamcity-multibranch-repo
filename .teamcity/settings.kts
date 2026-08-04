import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2026.1"

project {

    vcsRoot(HttpsGithubComValrravnAthanorRefsHeadsMaster)

    buildType(Build)

    pipeline(PipelinesWithDSL_Athanor23)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = """echo "Hello""""
        }
    }
})

object HttpsGithubComValrravnAthanorRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/Valrravn/Athanor#refs/heads/master"
    url = "https://github.com/Valrravn/Athanor"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "Valrravn"
        password = "credentialsJSON:3a592200-557f-45cf-b87f-91207c9ea1fc"
    }
    param("pipelines.connectionId", "PROJECT_EXT_31")
})


object PipelinesWithDSL_Athanor23 : Pipeline({
    id("Athanor23")
    name = "Athanor 23"

    repositories {
        repository(HttpsGithubComValrravnAthanorRefsHeadsMaster)
    }

    triggers {
        vcs {
        }
    }

    job(PipelinesWithDSL_Athanor23_Job1)
    job(PipelinesWithDSL_Athanor23_Job2)
    job(PipelinesWithDSL_Athanor23_Job3)
    job(PipelinesWithDSL_Athanor23_Job4)
})

object PipelinesWithDSL_Athanor23_Job1 : Job({
    id("Job1")
    name = "Job 1"
})

object PipelinesWithDSL_Athanor23_Job2 : Job({
    id("Job2")
    name = "Job 2"

    dependency(PipelinesWithDSL_Athanor23_Job1)
})

object PipelinesWithDSL_Athanor23_Job3 : Job({
    id("Job3")
    name = "Job 3"

    dependency(PipelinesWithDSL_Athanor23_Job2)
})

object PipelinesWithDSL_Athanor23_Job4 : Job({
    id("Job4")
    name = "Job 4"
})
