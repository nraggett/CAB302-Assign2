pipeline {
  agent any
  stages {
    stage('jenk1') {
      environment {
        ENV1 = 'environmentvariable'
      }
      steps {
        echo 'STARTING THIS JENKINS SHIT ON $ENV1'
      }
    }
  }
}