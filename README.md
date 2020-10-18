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
  - Perform helm release of jenkins.

Logs
```
Masking supported pattern matches of $TOKEN
[Pipeline] {
[Pipeline] sh
+ echo ****
+ gh auth login --with-token
[Pipeline] }
[Pipeline] // withCredentials
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Create jenkins config)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ rm -rf /Users/shisharm18/jenkins_docker_home/home/workspace/SelfService/Onboard-To-Jenkins/.git
+ gh repo view configuration-org/team-a-jenkins-config
GraphQL error: Could not resolve to a Repository with the name 'configuration-org/team-a-jenkins-config'.
+ export exitcode=1
+ exitcode=1
+ '[' 1 -ne 0 ']'
+ gh repo create configuration-org/team-a-jenkins-config -y --private
https://github.com/configuration-org/team-a-jenkins-config
+ gh repo clone configuration-org/team-a-jenkins-config
Cloning into 'team-a-jenkins-config'...
warning: You appear to have cloned an empty repository.
+ cp -r charts/jenkins/VALUES_SUMMARY.md team-a-jenkins-config/VALUES_SUMMARY.md
+ cp -r onboard/Readme.md team-a-jenkins-config/Readme.md
+ sed s/jenkins.example.com/team-a.test-jenkins.com/g charts/jenkins/values.yaml
+ cd team-a-jenkins-config
+ git add VALUES_SUMMARY.md values.yaml
+ git commit -m 'updating config code'
[master (root-commit) 8c787db] updating config code
 2 files changed, 1060 insertions(+)
 create mode 100644 VALUES_SUMMARY.md
 create mode 100644 values.yaml
+ git push -u origin master
To https://github.com/configuration-org/team-a-jenkins-config.git
 * [new branch]      master -> master
Branch 'master' set up to track remote branch 'master' from 'origin'.
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Cluster Auth)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ gcloud container clusters get-credentials cluster-1 --zone=us-central1-c
Fetching cluster endpoint and auth data.
kubeconfig entry generated for cluster-1.
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Install Jenkis)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ sed s/NAMESPACE/team-a-21/g onboard/namespace.yaml
+ sed s/NAMESPACE/team-a-21/g onboard/serviceaccount.yaml
+ kubectl apply -f onboard/namespace-apply.yaml
namespace/team-a-21 unchanged
+ kubectl apply -f onboard/serviceaccount-apply.yaml
serviceaccount/team-a-21-admin unchanged
role.rbac.authorization.k8s.io/team-a-21-admin unchanged
rolebinding.rbac.authorization.k8s.io/team-a-21-admin-rolebinding unchanged
clusterrole.rbac.authorization.k8s.io/team-a-21-ClusterRole unchanged
clusterrolebinding.rbac.authorization.k8s.io/team-a-21-ClusterRoleBinding unchanged
+ kubectl config set-context --current --namespace=team-a-21
Context "gke_vast-operator-277120_us-central1-c_cluster-1" modified.
+ helm upgrade --install jenkins-team-a-21 --values team-a-jenkins-config/values.yaml charts/jenkins/
Release "jenkins-team-a-21" has been upgraded. Happy Helming!
NAME: jenkins-team-a-21
LAST DEPLOYED: Sun Oct 18 11:23:07 2020
NAMESPACE: team-a-21
STATUS: deployed
REVISION: 2
NOTES:
1. Get your 'admin' user password by running:
  printf $(kubectl get secret --namespace team-a-21 jenkins-team-a-21 -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode);echo

2. Visit http://team-a.test-jenkins.com

3. Login with the password from step 1 and the username: admin

4. Use Jenkins Configuration as Code by specifying configScripts in your values.yaml file, see documentation: http://team-a.test-jenkins.com/configuration-as-code and examples: https://github.com/jenkinsci/configuration-as-code-plugin/tree/master/demos

For more information on running Jenkins on Kubernetes, visit:
https://cloud.google.com/solutions/jenkins-on-container-engine
For more information about Jenkins Configuration as Code, visit:
https://jenkins.io/projects/jcasc/
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Email Details)
[Pipeline] script
[Pipeline] {
[Pipeline] echo
TODO: Email jenkins details to user
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ rm -rf /tmp/token.txt
+ echo github logout
github logout
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```

## Future enhancement
- GHE oauth integration
- Enable SSL
- Enable Github orgnization job in jenkins