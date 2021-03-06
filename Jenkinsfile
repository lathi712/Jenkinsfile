pipeline {
  agent none
  environment {
        PROJECT_ID = 'kubernetes-sbk'
        CLUSTER_NAME = 'cluster-1'
        LOCATION = 'us-central1-b'
        CREDENTIALS_ID = 'gke'
}
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
        sh 'gcloud auth activate-service-account jenkins@kubernetes-sbk.iam.gserviceaccount.com --key-file=/home/sushanthbabu/kubernetes-sbk.json --project=kubernetes-sbk'
        sh 'docker build -t us.gcr.io/kubernetes-sbk/java:${BUILD_NUMBER} .'
        sh 'docker push us.gcr.io/kubernetes-sbk/java:${BUILD_NUMBER}'
       
      }
    }

    stage('Deploy to GKE') {
            agent any
            steps{
                //step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
                sh 'gcloud container clusters get-credentials cluster-1 --zone us-central1-b --project kubernetes-sbk'
                sh 'helm create springboot_${BUILD_NUMBER}'
                sh "sed -i 's#us.gcr.io/kubernetes-sbk/java:latest#us.gcr.io/kubernetes-sbk/java:${BUILD_NUMBER}#g' springboot/values.yaml"
                sh 'helm install --name springboot_${BUILD_NUMBER} ./springboot_${BUILD_NUMBER}'
        }
    }

  }
}