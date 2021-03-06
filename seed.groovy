import jenkins.model.Jenkins
import hudson.*


def jenkinsJob(jobName,repoUrl,credID,jenkinsFile) {
    pipelineJob(jobName).with {
        parameters {
                stringParam('teamName', 'team-a', 'team name')
                stringParam('jenkinsUrlPrefix', 'test-jenkins.com', 'Jenkins url prefix. Once provitioning is done jenkins url will be <teamName>.<jenkinsUrlPrefix> ex. http://team-a.test-jenkins.com/')
                stringParam('orgName', 'letscode-ss', 'Gihub orgnization name')
                stringParam('gheTokenCreds', 'teamA-ghe-token', 'Gihub Token credentail name')
                stringParam('OAuthCred', 'teamA-creds', 'Jenkins credentail for oauth')
                stringParam('costCenter', '21', 'cost center')
                stringParam('cluster', 'cluster-1', 'Cluster name')
                stringParam('region', 'us-central1-c', 'Cluster region')
                stringParam('project', 'vast-operator-277120', 'GCP project name')
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


def jobMap = ['Onboard-To-Jenkins': "https://github.com/letscode-ss/4-devops-with-jenkins.git"]

def rootFolder = "SelfService"

folder("${rootFolder}") {
    description('adhoc folder')
}

jobMap.each { job ->
    //Create nexus job
    jenkinsJob( "${rootFolder}/$job.key","$job.value","github_personal","onboard/Jenkinsfile")
}

