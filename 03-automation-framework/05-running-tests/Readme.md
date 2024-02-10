<Running> Tests

TestNg parameters

Running a test suite

java -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml
java -Dbrowser=chrome -cp 'libs/*' org.testng.TestNG -threadcount 1  test-suites/flight-reservation.xml