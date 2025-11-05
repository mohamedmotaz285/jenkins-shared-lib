def call(String deploymentFile, String serviceFile, String imageName, String tokenID, String apiServerID) {
    sh "sed -i 's|image: .*|image: ${imageName}|' ${deploymentFile}"

    withCredentials([
        string(credentialsId: tokenID, variable: 'TOKEN'),
        string(credentialsId: apiServerID, variable: 'APISERVER')
    ]) {
        sh "kubectl apply -f ${deploymentFile} --server=${APISERVER} --token=${TOKEN} --insecure-skip-tls-verify=true"
        sh "kubectl apply -f ${serviceFile} --server=${APISERVER} --token=${TOKEN} --insecure-skip-tls-verify=true"
    }
}
