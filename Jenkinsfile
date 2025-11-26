pipeline {

    agent { label 'dummy-node' }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Choose Browser for UI tests')
        choice(name: 'ENV', choices: ['QA', 'DEV'], description: 'Choose Environment')
        choice(name: 'SUITE', choices: ['testng.xml', 'APItest.xml', 'both'], description: 'Select Suite to Run')
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
                    echo "___________Build failed - Check Compilation Error_______"
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

                    script {

                        if (params.SUITE == 'testng.xml') {
                            echo "Running ONLY UI Test Suite..."
                            runSuite("testng.xml", true)
                        }

                        else if (params.SUITE == 'APItest.xml') {
                            echo "Running ONLY API Test Suite..."
                            runSuite("APItest.xml", false)
                        }

                        else if (params.SUITE == 'both') {
                            echo "Running BOTH UI and API Test Suites..."
                            runSuite("testng.xml", true)
                            runSuite("APItest.xml", false)
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

        stage('Publish Report') {
            steps {

                script {

                    // ------------------ UI REPORT -------------------
                    if (params.SUITE == 'testng.xml' || params.SUITE == 'both') {
                        echo "Publishing UI Reports..."
                        publishHTML(target: [
                            reportDir: 'Reports',
                            reportFiles: '*.html',
                            reportName: 'UI Extent Report'
                        ])
                    }

                    // ------------------ API REPORT -------------------
                    if (params.SUITE == 'APItest.xml' || params.SUITE == 'both') {
                        echo "Publishing API Reports..."
                        publishHTML(target: [
                            reportDir: 'ApiReports',
                            reportFiles: '*.html',
                            reportName: 'API Extent Report'
                        ])
                    }
                }
            }
        }
    }

    post {
        always {

            script {

                // Archive UI reports only when UI suite ran
                if (params.SUITE == 'testng.xml' || params.SUITE == 'both') {
                    archiveArtifacts artifacts: 'Reports/*.html', fingerprint: true
                }

                // Archive API reports only when API suite ran
                if (params.SUITE == 'APItest.xml' || params.SUITE == 'both') {
                    archiveArtifacts artifacts: 'ApiReports/*.html', fingerprint: true
                }
            }
        }
    }
}

def runSuite(String suiteName, boolean isUI) {

    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
        retry(2) {

            if (isUI) {

                bat """
                    mvn test ^
                        -DsuiteXmlFile=${suiteName} ^
                        -Dbrowser=${params.BROWSER} ^
                        -Denv=${params.ENV} ^
                        -Dusername=%USER% ^
                        -Dpassword=%PASS%
                """

            } else {

                bat """
                    mvn test ^
                        -DsuiteXmlFile=${suiteName}
                """
            }
        }
    }
}
