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


	echo '-------------------------------------------------------------------------\n----------------------- Initialisation des tests ------------------------'


	boolean testPassed = true
	/*** Test de fonctionnalité ***/ 
	stage('Test de fonctionnalite'){
		try{
			dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
			}
		}catch(Exception e){
			testPassed = false
		}	
	}

	if(testPassed = true){
		echo 'Test Ok'
	}else{
		echo 'Test dwehb'
	}

	/*** Test de sécurité ***/ 
	stage('Test de charge'){
		dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C securitytestrunner.bat -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
		}	
	}
}


// ******************** METHODES ******************** 
def cmd_exec(command) {
    return bat(returnStdout: true, script: "${command}").trim()
}
