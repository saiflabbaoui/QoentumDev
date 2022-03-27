pipeline{
      agent any
      environment
      {
        registry = "devopsqoentum/qoentum"
        registryCredential = 'devopsqoentum'
        dockerImage = ''
      }
    stages {
        stage('Checkout GIT'){
          steps{
            echo 'Pulling...'
            git url: 'https://github.com/saiflabbaoui/QoentumDev.git' ;
          }
        }
        stage("Package")
          {
            steps
            { sh """mvn clean install""";
              echo'test'
            }
          }

        stage("Nexus")
          {
          steps
              { sh """mvn deploy""";
              echo'nexus'
          }
        }

        stage('SonarQube analysis')
          {
              steps
                  { withSonarQubeEnv('Sonar Server')
                  { sh """mvn sonar:sonar"""
                  }
              }
        }

      stage('Building image')
          {
          steps
              {
              script { dockerImage = docker.build registry + ":$BUILD_NUMBER"
              }
          }
      }

      stage('Deploy Image')
      {
          steps
              { script
              {
              docker.withRegistry( '', registryCredential )
                  {
                  dockerImage.push("$BUILD_NUMBER")
                  dockerImage.push()
                  }
              }
          }
      }

      stage('Clean up') {
          steps {
              bat "docker rmi $registry:$BUILD_NUMBER"
          }
      }
      stage('Run image') {
          steps {
              bat "docker run -d --name qoentum --link mysql:mysql -p 8083:8083 $registry:$BUILD_NUMBER"
          }
      }


    }
    post
    {
        always {
      mail to: 'saif.labbaoui@esprit.tn',
      subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
      body: "${env.BUILD_URL} has result ${currentBuild.result}"
      }
    }
  }
