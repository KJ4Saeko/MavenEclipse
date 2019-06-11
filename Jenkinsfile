node {

	properties([
        parameters([
             text(name: "VERSION", defaultValue: 'master', description: "Git version")
        ])
    ])

	/*** Initialisation ***/ 
	boolean gitPassed = true
	stage ('Recuperation Deve'){
		try{
			echo '-------------------------------------------------------------------------\n--------------------------- Informations git ----------------------------'
			checkout scm
			bat "git checkout ${params.VERSION}"		
		}catch(Exception e){
			gitPassed = false
			echo 'Impossible de recuperer les informations de GIT.'
		}
	}

	stage('SCM'){
		git 'https://github.com/KJ4Saeko/MavenEclipse.git'
	}

	stage('SonarQube analyse'){
		def scannerHome = tool 'Scanner for MSBuild'
		withSonarQubeEnv('SonarQube'){
			bat "${sqScannerMsBuildHome}\\SonarQube.Scanner.MSBuild.exe begin /k:Test/n:TestSonar/v:1.0/d:sonar.host.url=http://localhost:9000/d:sonar.login=admin"
			bat 'MSBuild.exe /t:Rebuild' 
			bat "${sqScannerMsBuildHome}\\SonarQube.Scanner.MSBuild.exe begin /d:sonar.login=admin"
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
				/* Trouver une méthode pour push sur un git ici 
					et pour récuéprer par la même occasion */
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
			}
		}else{
			echo "Impossible d'executer les tests de fonctionnalite. (Probleme Deploiement -> Qual)"
		}	
	}
	if(!testPassedP1){
		echo 'Impossible de déployer sur le serveur de production, un problème est survenu sur le test de fonctionnalite'
	}


	/****** Test de sécurité ******/
	boolean testPassedP2 = true 
	stage('Test de charge'){
		if(deploiementQualPassed){
			try{
				dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
				cmd_exec('cmd.exe /C securitytestrunner.bat -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
				}	
			}catch(Exception e){
				testPassedP2 = false
			}	
		}else{
			echo "Impossible d'executer les tests de securite. (Probleme Deploiement -> Qual)"
		}

	}
	if(!testPassedP2){
		echo "Impossible de deployer sur le serveur de production, un probleme est survenu sur le test de securite"
	}
}




// ******************** METHODES ******************** 
def cmd_exec(command) {
    return bat(returnStdout: true, script: "${command}").trim()
}
