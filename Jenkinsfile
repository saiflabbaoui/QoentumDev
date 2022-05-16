pipeline{
      agent any
      environment
      {
        registry = "iness22/qoentum"
        registryCredential = 'docker-hub'
        dockerImage = ''
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
         
          
      stage('Building image')
          {
          steps
              {
              script { dockerImage = docker.build registry + ":$BUILD_NUMBER"
              }
          }
      }

      stage('Pushing Image')
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
              sh "docker rmi $registry:$BUILD_NUMBER"
          }
      }
    
          
      
      stage('Deploying App to Kubernetes') {
      steps {
         
        script {
          kubernetesDeploy(configs: "deployment.yml", kubeconfigId: "kubernetes")
        }
      }
      
      }


    }
      post
    {
        always {
      mail to: 'inesboukhris20@gmail.com',
      subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
      body: "${env.BUILD_URL} has result ${currentBuild.result}"
      }
    }
   
  }
