pipeline {
	
	
	
    agent any 
    stages {
        stage('Checkout GIT') { 
            steps {
			    echo 'Pulling...'
				git url : 'https://github.com/saiflabbaoui/QoentumDev.git' ;
            }
        }
        stage("Test, Build") {
            steps {
				sh """mvn clean install"""
            }
        }
	    
	    
		        stage("Package") {
            steps {
				sh """mvn clean package""";
				echo'test'
            }
        }
		
		stage("Nexus") {
            steps {
				sh """mvn deploy:deploy-file -DgroupId=com.sfm -DartifactId=qoentum -Dversion=1.1.0 -Dpackaging=war -DrepositoryId=release -Durl=http://192.168.22.226:8081/repository/kcmaven-release/ -Dfile=pom.xml""";
				echo'nexus'
            }
			}
		}
		post{
		success{
		emailext body: 'build success', subject: 'Jenkins', to: 'inesboukhris20@gmail.com'
		}
		failure{
		emailext body: 'build failure', subject: 'Jenkins', to: 'inesboukhris20@gmail.com'
		}
		
    }
}
