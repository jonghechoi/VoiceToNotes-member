def APP_NAME = 'member-management-0.0.1-SNAPSHOT'
def JAR_PATH = 'build/libs/${APP_NAME}.jar'
def DOCKER_HUB_USERNAME = 'sosinnmi2'
def DOCKER_HUB_REPOSITORY = 'member'
def DOCKER_IMAGE_TAG = 'latest'

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
                    echo 'Git Repo Checkout Succeed.'
                }
                failure {
                    echo 'Git Repo Checkout Failed.'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'chmod 777 ./gradlew'
                sh './gradlew clean bootJar -Pprofile=dev'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    def API_URL = "https://hub.docker.com/v2/repositories/${DOCKER_HUB_USERNAME}/${DOCKER_HUB_REPOSITORY}/tags/"
                    def JSON_DATA = sh(script: "curl -s ${API_URL}", returnStdout: true).trim()
                    def SLURPER = new JsonSlurper()
                    int LATEST_TAG = SLURPER.parseText(JSON_DATA).results[0].name
                    int NEW_LATEST_TAG = LATEST_TAG + 0.1

                    echo "Latest Docker Image Tag: ${NEW_LATEST_TAG}"

//                     docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-id') {
//                         docker.image("${DOCKER_HUB_USERNAME}/${DOCKER_HUB_REPOSITORY}:${DOCKER_IMAGE_TAG}").pull()
//
//                         image = docker.build("${DOCKER_HUB_USERNAME}/${DOCKER_HUB_REPOSITORY}")
//                         image.push("${RELEASE_NOTES}")
//                     }
                }
            }
        }

        stage('Deployment') {
            steps {
                // 컨테이너 접속

                // jar 파일 교체

                echo "Jenkins Test15"
                echo "${APP_NAME}"
                echo "${JAR_PATH}"
            }
        }
    }
}