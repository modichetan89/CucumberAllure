pipeline {
    agent any
 
    stages {
        stage('Test') {
            steps {
                bat "mvn -D clean test"
            }
        }
        stage('Cucumber Reports'){
            steps{
                cucumber fileIncludePattern: "**/cucumber.json",
                    jsonReportDirectory: 'target'
            }
        }
        stage('Allure reports'){
            steps{
                echo 'Allure reports generating'
            }
            post {                
                success { allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
                }
            }
        }
        stage('Send Teams Notifications'){
            steps{
                echo 'Sending teams notification'
            }
            post {                       
                always{
                    office365ConnectorSend message: "Automation Status and Reports  \n Build Status: ${currentBuild.currentResult} \n Job Name: ${env.JOB_NAME} \n Build Number: ${env.BUILD_NUMBER} \n Build URL: ${env.BUILD_URL}", webhookUrl: 'https://fisglobal.webhook.office.com/webhookb2/e270f6eb-5f24-41bd-9cf5-4e01f5106673@e3ff91d8-34c8-4b15-a0b4-18910a6ac575/JenkinsCI/3ae4b5200e31489ebf1720c2d4380678/a3507bff-e4ad-4fb5-8873-bc472d4e3d83'
                }
             }
        }
               
    }      
}
