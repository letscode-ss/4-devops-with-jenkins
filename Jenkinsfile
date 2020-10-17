pipeline {
    agent { label 'master' }
    stages {
        stage("seed") { 
            steps {
                script { 
                        jobDsl targets: 'seed.groovy'
                }
            }
        }
    }
}
