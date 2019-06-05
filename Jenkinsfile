node {

	properties([
        parameters([
             text(name: "VERSION", defaultValue: 'master', description: "Git version")
        ])
    ])

	//Initialisation
	stage ('Initialisation') {
		echo 'Premiere etape ' + env.BRANCH_NAME
		checkout scm
		bat "git checkout ${params.VERSION}"
	}

	stage('Premier Test'){
		cmd_exec('cd C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/')
		cmd_exec('testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
	}
}

def cmd_exec(command) {
    return bat(returnStdout: true, script: "${command}").trim()
}