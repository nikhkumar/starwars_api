node {
    stage 'Clone the project'
    git 'https://github.com/PSEH-5/nikhil-starwars.git'

	stage('Checkout'){
   		echo "Pulling the code from Git"
   	}
   stage('Build'){
   		echo "Building the application"
   		sh "mvn install -DskipTests=true"
   }
   stage('Test'){
   		echo "Test Stage"
        sh "mvn test -Dspring.profiles.active=test"      
   }
   stage('Build'){
   		echo "Building the application"
   		sh "mvn install -DskipTests=true"
   }
   
   stage('Analysis'){
   		echo "Analysing the code"
   }
   