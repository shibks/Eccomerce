pipeline {
    agent { label 'dummy-node' }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
