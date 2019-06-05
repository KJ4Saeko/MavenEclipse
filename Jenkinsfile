node {

	properties([
        parameters([
             text(name: "VERSION", defaultValue: 'master', description: "Git version")
        ])
    ])

	//Initialisation
	stage ('Initialisation') {
		echo 'Premiere etape' + env.BRANCH_NAME
		checkout scm
		bat "git checkout ${params.VERSION}"
	}

	stage('Premier Test'){
		bat "cd C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"
		echo 'Current Directory' + %CD%
		//cmd.exe /C testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml"
	}
}