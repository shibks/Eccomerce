pipeline {

    agent { label 'dummy-node' }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Choose Browser to run tests')
        choice(name: 'ENV', choices: ['QA', 'DEV'], description: 'Choose Environment')
    }

    stages {

        stage('Build') {
            steps {
                catchError(buildResult:'FAILURE', stageResult:'FAILURE') {
                    bat "mvn clean install -DskipTests"
                }
            }
            post {
                failure {
                    echo "___________Build failed - Check COMPILATION ERROR_______"
                }
            }
        }

        stage('Run Tests') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'login-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {

                    catchError(buildResult:'SUCCESS', stageResult:'FAILURE') {
                        retry(2) {
                            bat """
                                mvn test ^
                                -Dbrowser=${params.BROWSER} ^
                                -Denv=${params.ENV} ^
                                -Dusername=%USER% ^
                                -Dpassword=%PASS%
                            """
                        }
                    }

                }
            }

            post {
                failure {
                    echo "Tests failed! Collecting screenshots..."

                    archiveArtifacts artifacts: 'screenshots/*.png', fingerprint: true
                }
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
