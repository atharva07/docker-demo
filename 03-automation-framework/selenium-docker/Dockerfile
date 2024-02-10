FROM bellsoft/liberica-openjdk-alpine:17-cds-x86_64

# workspace
WORKDIR /home/selenium-docker

# we can add the required file to run the test
ADD target/docker-resources ./

# environment variables
# BROWSER
# HUB_HOST
# TEST-SUITE
# THREAD-COUNT

# Run the command to initiate the tests
ENTRYPOINT java -cp 'libs/*' -Dselenium.grid.enabled=true \
           -Dselenium.grid.hubHost=${HUB_HOST} \
           -Dbrowser=${BROWSER} \
           org.testng.TestNG \
           -threadcount ${THREAD_COUNT} \
           test-suites/${TEST_SUITE}