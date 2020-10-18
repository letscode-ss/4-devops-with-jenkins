# 4-devops-with-jenkins

In this project i have create solution to provition and manage jenkins instance for multiple teams in orgnization.

## Prerequisite
- github orgnization
- Jenkins instance
- kuberenetes cluster (GKE)

## How this solution works
- Configure this repository as pipeline job in jenkins using Jenkinsfile.
- It will create Onboard-To-Jenkins job under SelfService folder.
![snap-1](docs//snap-1.png)
![snap-2](docs//snap-2.png)
- This job perform below task
  - Copy default vaules.yaml and create repo
  - Create a namespace for the team 
  - Create a service account for team.




## Future enhancement
- GHE oauth integration
- Enable SSL
- Enable Github orgnization job in jenkins