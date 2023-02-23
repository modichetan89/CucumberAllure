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
            post {                       
                always{
                    office365ConnectorSend color: '5FB41C', message: 'Automation Status and Reports  ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)', status: '${currentBuild.currentResult}', webhookUrl: 'https://fisglobal.webhook.office.com/webhookb2/e270f6eb-5f24-41bd-9cf5-4e01f5106673@e3ff91d8-34c8-4b15-a0b4-18910a6ac575/JenkinsCI/3ae4b5200e31489ebf1720c2d4380678/a3507bff-e4ad-4fb5-8873-bc472d4e3d83'
                }
             }
        }
               
    }      
}
