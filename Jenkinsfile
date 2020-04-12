pipeline {
  agent none
  stages {
    stage('Maven Install') {
        agent {
            docker {
            image 'maven:3.5.0'
            }
        }
        steps {
            sh 'mvn clean install'
        }
    }

    stage('Docker Build') {
      agent any
      steps {
        sh 'gcloud auth activate-service-account jenkins@kubernetes-sbk.iam.gserviceaccount.com --key-file=$HOME/kubernetes-sbk.json --project=kubernetes-sbk'
        sh 'docker build -t us.gcr.io/kubernetes-sbk/java:${BUILD_NUMBER} .'
        sh 'docker push us.gcr.io/kubernetes-sbk/java:${BUILD_NUMBER}'
       
      }
    }

  }
}