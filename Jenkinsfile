def APP_NAME = 'member-management-0.0.1-SNAPSHOT'
def JAR_PATH = 'build/libs/${APP_NAME}.jar'

pipeline {
    agent any

    environment {
        GIT_CHECK_URL = 'https://github.com/jonghechoi/member-maganement.git'
        GIT_CHECKOUT_BRANCH = 'master'
        GIT_CREDENTIALS_ID = 'ListCheckr-Member-App-AccessToken'
    }

    stages {
        stage('Git Checkout') {
            steps {
                SCM_VARS = git branch: "${GIT_CHECKOUT_BRANCH}", credentialsId: "${GIT_CREDENTIALS_ID}", url: "${GIT_CHECKOUT_URL}"
            }

//             post {
//                 success {
//                     echo '\n\n\n Git Repo Checkout Succeed. \n\n\n'
//                 }
//                 failure {
//                     error '\n\n\n Git Repo Checkout Failed. \n\n\n'
//                 }
//             }
        }

        stage('Build') {
            steps {
                sh '''
                ./gradlew clean bootJar -Pprofile=dev
                '''
            }

//             post {
//                 success {
//                     echo '\n\n\n Build Succeed. \n\n\n'
//                 }
//                 failure {
//                     error '\n\n\n Build Failed. \n\n\n'
//                 }
//             }
        }

        stage('Test') [
            steps {
                sh '''
                ./gradlew test
                '''
            }

//             post {
//                 success {
//                     echo '\n\n\n Test Succeed. \n\n\n'
//                 }
//                 failure {
//                     error '\n\n\n Test Failed. \n\n\n'
//                 }
//             }
        ]

        stage('Deployment') {
            steps {
                echo 'Jenkins Test4'
                echo '$(APP_NAME)'
                echo '$(JAR_PATH)'
            }
        }
    }
}