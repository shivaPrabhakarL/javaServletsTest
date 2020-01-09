pipeline{
	agent{
		dockerfile true
	}
	stages{
		stage('Deploy'){
			steps{
				cd To-Do
				./mvnw clean install
				docker build -t image .
				docker run -p 8080 image
			}
		}
	}
}
