pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
