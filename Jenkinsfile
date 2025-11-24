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

        stage('Publish Extent Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'Reports',
                    reportFiles: '*.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'Reports/*.html', fingerprint: true
        }
    }
}
