import jenkins.model.Jenkins
import hudson.*


def jenkinsJob(jobName,repoUrl,credID,jenkinsFile) {
    pipelineJob(jobName).with {
        parameters {
                stringParam('teamName', 'test-X', 'team name')
                stringParam('jenkinsUrl', 'jenkins.com', 'Jenkins url prefix')
                stringParam('orgName', 'jenkins.com', 'Jenkins url prefix')
                stringParam('OAuthClientID', 'XXXXXXXXXXX', 'OAuth application client id')
                stringParam('OAuthClientSecret', 'XXXXXXXXX', 'OAuth application client secret(NOT SECURE)')
        }
        logRotator(-1, 10, -1, -1) 
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(repoUrl)
                                credentials(credID)
                            }
                            branch('master')
                        }
                }
                scriptPath(jenkinsFile)
            }
        }
    }
}


def jobMap = ['Onboard-To-Jenkins': "https://github.com/configuration-org/4-devops-with-jenkins.git"]

def rootFolder = "SelfService"

folder("${rootFolder}") {
    description('adhoc folder')
}

jobMap.each { job ->
    //Create nexus job
    jenkinsJob( "${rootFolder}/$job.key","$job.value","github_personal","onboard/Jenkinsfile")
}

