pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-21'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                dir('generated-client') {
                    sh 'mvn clean test'
                }
            }
        }
    }

    post {

        always {
            junit 'generated-client/target/surefire-reports/*.xml'
        }

        success {
            echo 'All tests passed'
        }

        failure {
            echo 'Some tests failed'
        }
    }
}