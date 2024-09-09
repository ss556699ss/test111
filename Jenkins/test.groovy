pipeline {
    agent any
    triggers {
       // cron('* * * * *')  每分鐘檢查一次
    }
    stages {
        stage('Check GitHub Issues') {
            steps {
                script {

                }
            }
        }
    }
}
