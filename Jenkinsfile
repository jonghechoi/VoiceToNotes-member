import groovy.json.JsonSlurper

def JAR_PATH = 'build/libs'
def JAR_FILE = 'member-management-0.0.1-SNAPSHOT.jar'

def TARGET_DOCKER_CONTAINER = 'member-management'
def DOCKER_HUB_REGISTRY = 'https://registry.hub.docker.com'
def DOCKER_HUB_USERNAME = 'sosinnmi2'
def DOCKER_HUB_REPOSITORY = 'member'
def DOCKER_IMAGE_TAG = 'latest'

pipeline {
    agent any

    environment {
        GIT_CHECKOUT_URL = 'https://github.com/jonghechoi/member-maganement.git'
        GIT_CHECKOUT_BRANCH = 'master'
        GIT_CREDENTIALS_ID = 'ListCheckr-Member-App-AccessToken'

        DOCKER_HUB_CREDENTIALS_ID = 'dockerhub-id'
    }

    stages {
        stage('Git Checkout') {
            steps {
                script {
                    SCM_VARS = git branch: "${GIT_CHECKOUT_BRANCH}", credentialsId: "${GIT_CREDENTIALS_ID}", url: "${GIT_CHECKOUT_URL}"
                }
            }
        }

        stage('Build') {
            steps {
                sh 'chmod 777 ./gradlew'
                sh './gradlew clean bootJar -Pprofile=local'
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
                    def JSON_DATA = sh (script: "curl -s ${API_URL}", returnStdout: true).trim()
                    def LATEST_TAG = new JsonSlurper().parseText(JSON_DATA).results[0].name
                    def NEW_LATEST_TAG = (LATEST_TAG as Double) + 0.1

                    echo "New Docker Image Tag: ${NEW_LATEST_TAG}"

                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-id') {
                        image = docker.build("${DOCKER_HUB_USERNAME}/${DOCKER_HUB_REPOSITORY}")
                        image.push("${NEW_LATEST_TAG.toString()}")
                    }
                }
            }
        }

        stage('Deployment') {
            steps {
                echo 'test3'
                // 기존 컨테이너 내 jar 파일 교체
                sh "docker exec -i ${TARGET_DOCKER_CONTAINER} rm -r /app/app.jar"
                sh "docker cp ${JAR_PATH}/${JAR_FILE} ${TARGET_DOCKER_CONTAINER}:/app/app.jar"
                sh "docker exec -i ${TARGET_DOCKER_CONTAINER} java -jar -Dspring.profiles.active=dev /app/app.jar"
            }
        }
    }
}