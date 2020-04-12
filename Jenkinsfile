pipeline {

    environment {
        registry = "us.gcr.io/kubernetes-sbk/"
        //registryCredential = 'dockerhub'
        dockerImage = ''
    }
    
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    // stage('Cloning Git') {
    //   steps {
    //     git 'https://github.com/gustavoapolinario/microservices-node-example-todo-frontend.git'
    //   }
    // }
    
    // options {
    //     skipStagesAfterUnstable()
    
    // }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        // stage('Test') {
        //     steps {
        //         sh 'mvn test'
        //     }
        //     post {
        //         always {
        //             junit 'target/surefire-reports/*.xml'
        //         }
        //     }
        // }
        stage('Building image') {
            agent {
                docker {
                //sh 'docker build -t us.gcr.io/kubernetes-sbk/java:+${BUILD_NUMBER}'
                 script {
                     dockerImage = docker.build registry + "java:$BUILD_NUMBER"
                 }
            }
            }
            
        }
        stage('Deploy Image') {
            steps{
                script {
                    docker.withRegistry( '') {
                    dockerImage.push()
                    }
                }
            }
        }
        // stage('Deliver') { 
        //     steps {
        //         sh './jenkins/scripts/deliver.sh' 
        //     }
        // }
        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry:$BUILD_NUMBER"
            }
        }
    }
}