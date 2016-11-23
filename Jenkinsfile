def mavenName = 'mvn'
def jdkName = 'jdk7'

node('highram&&docker') {
    stage 'plugin'
    dir('plugin') {
        checkout scm
        timeout(30) {
            withMaven(jdk: jdkName, maven: mavenName) {
                sh 'mvn clean install -B'
            }
        }
    }
    stage 'Acceptance Tests'
    dir('ath') {
        git changelog: false, poll: false, url: 'https://github.com/jenkinsci/acceptance-test-harness.git'
        sh 'wget http://mirrors.jenkins.io/war-stable/latest/jenkins.war -O jenkins.war'
        try {
            withMaven(jdk: jdkName, maven: mavenName) {
                withEnv(['BROWSER=firefox', 'JENKINS_WAR=jenkins.war']) {
                    sh 'mvn clean test -Dmaven.test.failure.ignore=true -DforkCount=1 -B -Dform-element-path-plugin.jpi=../plugin/target/*.hpi'
                }
            }
        } finally {
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
        }
    }
}
