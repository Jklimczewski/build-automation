pipeline {
    agent any
    tools {
        maven 'M3'
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }
    stage('Docker Build') {
                    steps {
                        script {
                            docker.build("lukaszrynski/test-rest-api:${TAG}")
                        }
                    }
                }
                stage('Pushing Docker Image to Dockerhub 2') {
                    when {
                          branch "master"
                    }
                    steps {
                        script {
                            docker.withRegistry('https://registry.hub.docker.com', 'docker_credential') {
                                docker.image("lukaszrynski/test-rest-api:${TAG}").push()
                                docker.image("lukaszrynski/test-rest-api:${TAG}").push("latest")
                            }
                        }
                    }
                }
    }
}
