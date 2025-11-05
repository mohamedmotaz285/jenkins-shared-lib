def call(String dockerImage, String imageTag, String dockerHubCredID) {
    sh "docker build -t ${dockerImage}:${imageTag} ."

    withCredentials([usernamePassword(
        credentialsId: dockerHubCredID,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        sh """
            docker login -u "$DOCKER_USER" -p "$DOCKER_PASS"
            docker push ${dockerImage}:${imageTag}
        """
    }

    sh "docker rmi ${dockerImage}:${imageTag}"
}
