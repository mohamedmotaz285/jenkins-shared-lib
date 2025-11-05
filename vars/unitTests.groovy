def call() {
    echo "Running unit tests..."
    sh "mvn clean test"
}
