pipeline {

    agent { label 'dummy-node' }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Choose Browser to run tests')
        choice(name: 'ENV', choices: ['QA', 'DEV'], description: 'Choose Environment')
    }

    stages {

        stage('Build') {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn test -Dbrowser=${params.BROWSER} -Denv=${params.ENV}"
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
