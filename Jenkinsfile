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
        script {
            SCM_VARS = git branch: "${GIT_CHECKOUT_BRANCH}", credentialsId: "${GIT_CREDENTIALS_ID}", url: "${GIT_CHECKOUT_URL}"
        }

        stage('Git Checkout') {
            steps {

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
                echo 'Jenkins Test10'
                echo '$(APP_NAME)'
                echo '$(JAR_PATH)'
            }
        }
    }
}