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

		stage("Sonar")
		{
            steps 
			{
				bat """mvn sonar:sonar""";
				echo'sonar'
            }
        }
	
		
    }
}
