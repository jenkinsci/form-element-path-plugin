def mavenName = 'mvn'
def jdkName = 'jdk7'

node('highram&&docker') {
    withEnv(["JAVA_HOME=${tool jdkName}", "PATH+MAVEN=${tool mavenName}/bin:${env.JAVA_HOME}/bin"]) {
        stage('plugin') {
            dir('plugin') {
                checkout scm
                timeout(30) {
                    sh 'mvn clean install -B'
                }
            }
        }

        stage('Acceptance Tests') {
            dir('ath') {
                git changelog: false, poll: false, url: 'https://github.com/jenkinsci/acceptance-test-harness.git'

                withEnv(['BROWSER=firefox', 'JENKINS_WAR=jenkins.war']) {
                    def args = '-Dmaven.test.failure.ignore=true -DforkCount=1 -B -Dform-element-path-plugin.jpi=../plugin/target/*.hpi'
                    sh 'eval $(./vnc.sh) && mvn clean && ./run.sh firefox latest $args'
                }

                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
    }
}
