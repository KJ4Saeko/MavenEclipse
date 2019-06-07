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


	echo '-------------------------------------------------------------------------\n--------------------------- Initialisation des tests ----------------------------'


	/*** Test de fonctionnalité ***/ 
	stage('Test de fonctionnalite'){
		dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C testrunner.bat -sMultiple_TestSuite2 -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
		}	
	}


	/*** Test de sécurité ***/ 
	stage('Test de charge'){
		dir("C:/Program Files (x86)/SmartBear/SoapUI-5.5.0/bin/"){
			cmd_exec('cmd.exe /C securitytestrunner.bat -r C:/Users/ADM_LHO/Documents/Calculateur/Calculateur-soapui-project.xml')
		}	
	}

 	stage('check'){
        for(jobname in ['SomeJob', 'AnotherJob']){
            jobStatus = getJobStatus(jobName)
            echo jobStatus
            if(jobStatus == "SUCCESS" || jobStatus == "UNSTABLE"){
                any_success = true
                break
            }
        }
    }
}


// ******************** METHODES ******************** 
def cmd_exec(command) {
    return bat(returnStdout: true, script: "${command}").trim()
}


def getJobStatus(String jobName){
    def rx = httpRequest "https://jenkins.example.com/job/${jobName}/lastBuild/api/json"
    def rxJson = new JsonSlurper().parseText(rx.getContent())
    return rxJson['result']
}
