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
                    office365ConnectorSend message: "<b><I>Latest Automation Status and Reports: </I></b>  <br> <br> <table> <tr> <td> <b>Build Status: </b> </td> <td>${currentBuild.currentResult}</td> </tr> <tr> <td> <b>Job Name: </b> </td> <td>${env.JOB_NAME}</td> </tr> <tr> <td> <b>Build Number: </b> </td> <td>${env.BUILD_NUMBER}</td> </tr> <tr> <td> <b>Build URL: </b> </td> <td><a href=${env.BUILD_URL}> ${env.BUILD_URL} </a></td> </tr> <tr> <td> <b>Cucumber Report: </b> </td> <td><a href=${env.BUILD_URL+'cucumber-html-reports/overview-features.html'}> To view cucumber report click here </a></td> </tr> <tr> <td> <b>Allure Report: </b> </td> <td><a href=${env.BUILD_URL+'allure/'}> To view allure report click here </a></td> </tr> <table> ", webhookUrl: 'https://fisglobal.webhook.office.com/webhookb2/e270f6eb-5f24-41bd-9cf5-4e01f5106673@e3ff91d8-34c8-4b15-a0b4-18910a6ac575/JenkinsCI/3ae4b5200e31489ebf1720c2d4380678/a3507bff-e4ad-4fb5-8873-bc472d4e3d83'
                }
             }
        }
               
    }      
}
