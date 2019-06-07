node {

	properties([
        parameters([
             text(name: "VERSION", defaultValue: 'master', description: "Git version")
        ])
    ])

	//Initialisation
	stage ('Initialisation') {
		echo env.BRANCH_NAME + ' YOYOYOYOYOYOYOYOOYYOOYOYOYOY'
		echo 'Premiere etape ' + env.BRANCH_NAME
		echo '----------------------------------------------------'
		echo '---------------Informations diverses----------------'
		checkout scm
		bat "git checkout ${params.VERSION}"
	}

	//Test de fonctionnalité
	stage('Test de fonctionnalite'){
		dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
		}	
	}


	// Test de sécurité
	stage('Test de charge'){
		dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C securitytestrunner.bat -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
		}	
	}
}

def cmd_exec(command) {
    return bat(returnStdout: true, script: "${command}").trim()
}