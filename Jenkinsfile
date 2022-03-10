pipeline 
{
	
	
	
    agent any 
    stages {

        stage('Checkout GIT') 
		{ 
            steps 
			{
			    echo 'Pulling...'
				git url : 'https://github.com/saiflabbaoui/QoentumDev.git' ;
            }
        }

		stage("Package") 
		{
            steps
			 {
				sh """mvn clean package""";
				echo'test'
            }
        }
		
		stage("Nexus") 
		{
            steps 
			{
				sh """mvn deploy""";
				echo'nexus'
            }
		}

	    	 stage('SonarQube analysis') 
	    	{
            steps 
			{
                		withSonarQubeEnv('Sonar Server') 
				{
                    		sh """mvn sonar:sonar"""
                		}
            		}
        	}
	    
	    
    }
	
	post
		{
			success
			{
			emailext body: 'build success', subject: 'Jenkins', to: 'inesboukhris20@gmail.com'
		    }
		
			failure
			{
			emailext body: 'build failure', subject: 'Jenkins', to: 'inesboukhris20@gmail.com'
			}
		}
}
