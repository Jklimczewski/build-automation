pipeline {
    agent any
    tools {
        maven 'M3'
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }
    stages {
        stage('Docker Build') {
            steps {
                script {
                    docker.build("xkuklix/proj-aut:${TAG}")
                }
            }
        }
            stage('Pushing Docker Image to Dockerhub 2') {
                when {
                    branch "main"
                }
                steps {
                    script {
                        docker.withRegistry('https://registry.hub.docker.com', 'docker_cred') {
                            docker.image("xkuklix/proj-aut:${TAG}").push()
                            docker.image("xkuklix/proj-aut:${TAG}").push("latest")
                        }
                    }
                }
            }
    }
}
