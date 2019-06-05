node {

	//Initialisation
	stage ('Initialisation') {
		echo 'Premiere etape' + env.BRANCH_NAME
	}

	stage('Premier Test'){
		bat "cd C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"
		cmd.exe /C testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml
	}
}