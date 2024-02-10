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
# once the changes are made in the automation script
# 1. create a package by hitting the command - mvn clean package -DskipTests
# 2. Then build  the image using the command - docker build -t=[username]/[imagename]
# 3. Then hit the command to execute the test - docker run -e BROWSER=chrome -e HUB_HOST=192.168.0.104 -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=2 atharvahiwase7/dockerselenium

# Run the command to initiate the tests
ENTRYPOINT java -cp 'libs/*' -Dselenium.grid.enabled=true \
           -Dselenium.grid.hubHost=${HUB_HOST} \
           -Dbrowser=${BROWSER} \
           org.testng.TestNG \
           -threadcount ${THREAD_COUNT} \
           test-suites/${TEST_SUITE}