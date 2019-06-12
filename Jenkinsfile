node {

	properties([
        parameters([
             text(name: "VERSION", defaultValue: 'master', description: "Git version")
        ])
    ])

	/*** Initialisation ***/ 
	boolean gitPassed = true
	stage ('Recuperation Dev'){
		try{
			echo '-------------------------------------------------------------------------\n--------------------------- Informations git ----------------------------'
			checkout scm
			cmd_exec("git checkout ${params.VERSION}")	
		}catch(Exception e){
			gitPassed = false
			echo 'Impossible de recuperer les informations de GIT.'
		}
	}

	//stage('SCM'){
	//	git 'https://github.com/KJ4Saeko/MavenEclipse.git'
	//}

	/* Insérer ici le code permettant de récuéprer le code à analyser sur git 
	   pour ainsi l'analyser avec sonarQube - Une autre étape de build derva être faite ici 
	   pour récupérer les fichier compilé */


	echo '-------------------------------------------------------------------------\n----------------------- Analyse du code ------------------------'

	/* Analyse du code avec SonarQube */
	stage('SonarQube analyse') {
    	// requires SonarQube Scanner 2.8+
    	def scannerHome = tool 'SonarQube Scanner 3.3';
    	withSonarQubeEnv('Sonarqube') {
      		bat "${scannerHome}/sonar-scanner.bat"
    	}
  	}

	/*** Deploiement ***/ 
	boolean deploiementQualPassed = true
	stage('Build'){
		if(gitPassed){
			try{
				File ftest1 = new File ("C:/jenkins/workspace/RealPipeline/testQual.txt") 
				ftest1.createNewFile()
				FileWriter ftest1f = new FileWriter(ftest1)
				ftest1f.write(" Build :" + BUILD_NUMBER + " SUCCESS.\n")
				ftest1f.close()
				echo "Build SUCCESS"
				/* Trouver une méthode pour push sur un git ici 
					et pour récuéprer par la même occasion 
				bat "git add report-task.txt"
				bat "git merge test"
				bat 'git commit -am "Merge to test branch"'
				bat "git push origin master" */
			}catch (Exception e){
				echo "Impossible de deployer en Qual, un probleme est survenu"
				deploiementQualPassed = false
			}
		}	
	}

	echo '-------------------------------------------------------------------------\n----------------------- Initialisation des tests ------------------------'


	/****** Test de fonctionnalité ******/
	boolean testPassedP1 = true 
	stage('Test de fonctionnalite'){
		if(deploiementQualPassed){
			try{
				dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
					cmd_exec('cmd.exe /C testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
				}
			}catch(Exception e){
				testPassedP1 = false
				echo "Test de fonctionnalite failed."
			}
		}else{
			echo "Impossible d'executer les tests de fonctionnalite. (Probleme Deploiement -> Qual)"
		}	
	}
	if(!testPassedP1){
		echo 'Impossible de deployer sur le serveur de production, un problème est survenu sur le test de fonctionnalite.'
	}

	/****** Test de Charge ******/
	boolean testPassedP2 = true
	stage('Test de charge'){
		if(deploiementQualPassed){
			try{
				dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
					cmd_exec("cmd.exe /C loadtestrunner.bat C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml")
				}
			}catch(Exception e){
				testPassedP2 = false
				echo "Test de charge failed."
			}
		}else{
			echo "Impossible d'executer les tests de charge. (Probleme Deploiement -> Qual)"
		}
	}
	if(!testPassedP2){
		echo 'Impossible de deployer sur le serveur de production, un problème est survenu sur le test de charge.'
	}

	/****** Test de sécurité ******/
	boolean testPassedP3 = true 
	stage('Test de securite'){
		if(deploiementQualPassed){
			try{
				dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
					cmd_exec('cmd.exe /C securitytestrunner.bat -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
				}	
			}catch(Exception e){
				testPassedP3 = false
				echo "Test de securite failed."
			}	
		}else{
			echo "Impossible d'executer les tests de securite. (Probleme Deploiement -> Qual)"
		}

	}
	if(!testPassedP3){
		echo "Impossible de deployer sur le serveur de production, un probleme est survenu sur le test de securite."
	}
}




// ******************** METHODES ******************** 
def cmd_exec(command) {
    return bat(returnStdout: true, script: "${command}").trim()
}
