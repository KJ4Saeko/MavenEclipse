node {

	def any_success = false

	properties([
        parameters([
             text(name: "VERSION", defaultValue: 'master', description: "Git version")
        ])
    ])

	/*** Initialisation ***/ 
	stage ('Initialisation'){
		echo '-------------------------------------------------------------------------\n--------------------------- Informations git ----------------------------'
		checkout scm
		bat "git checkout ${params.VERSION}"
	}

	stage('Deploiement prod'){
		if(buildPassed){
			try{
				File ftest1 = new File ("C:\jenkins\workspace\RealPipeline\testQual.txt") 
				ftest1.createNewFile()
				FileWriter ftest1f = new FileWriter(ftest1)
				ftest1f.write(" Build :" + BUILD_NUMBER + " reussi.\n")
				ftest1f.close()
			}catch (Exception e){
				echo "Impossible de deployer, un probleme est survenu"
			}
		}	
	}
	

	echo '-------------------------------------------------------------------------\n----------------------- Initialisation des tests ------------------------'


	/****** Test de fonctionnalité ******/

	boolean testPassedP1 = true 
	stage('Test de fonctionnalite'){
		try{
			dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
			}
		}catch(Exception e){
			testPassedP1 = false
		}	
	}
	if(!testPassedP1){
		echo 'Impossible de déployer sur le serveur de production, un problème est survenu sur le test de fonctionnalite'
	}


	/****** Test de sécurité ******/

	boolean testPassedP2 = true 
	stage('Test de charge'){
		try{
			dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C securitytestrunner.bat -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
			}	
		}catch(Exception e){
			testPassedP2 = false
		}	
	}
	if(!testPassedP2){
		echo 'Impossible de déployer sur le serveur de production, un problème est survenu sur le test de securite'
	}
}




// ******************** METHODES ******************** 
def cmd_exec(command) {
    return bat(returnStdout: true, script: "${command}").trim()
}
