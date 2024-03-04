def APP_NAME = 'member-management-0.0.1-SNAPSHOT'
def JAR_PATH = 'build/libs/${APP_NAME}.jar'

pipeline {
    agent any

    environment {
        GIT_CHECKOUT_URL = 'https://github.com/jonghechoi/member-maganement.git'
        GIT_CHECKOUT_BRANCH = 'master'
        GIT_CREDENTIALS_ID = 'ListCheckr-Member-App-AccessToken'
    }

    stages {
        stage('Git Checkout') {
            steps {
                script {
                    SCM_VARS = git branch: "${GIT_CHECKOUT_BRANCH}", credentialsId: "${GIT_CREDENTIALS_ID}", url: "${GIT_CHECKOUT_URL}"
                }
            }
            post {
                success {
                    echo '\n\n\n Git Repo Checkout Succeed. \n\n\n'
                }
                failure {
                    echo '\n\n\n Git Repo Checkout Failed. \n\n\n'
                }
            }
        }

        stage('Build') {
            steps {
                sh '''
                ./gradlew clean bootJar -Pprofile=dev
                '''
            }
        }

        stage('Test') {
            steps {
                sh '''
                ./gradlew test
                '''
            }
        }

        stage('Deployment') {
            steps {
                echo 'Jenkins Test14'
                echo '$(APP_NAME)'
                echo '$(JAR_PATH)'
            }
        }
    }
}