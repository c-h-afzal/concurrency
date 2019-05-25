This repository includes the examples and the solutions discussed in the course "Multithreading and Concurrency for 
Senior Engineering Interviews" on educative.io

The project can be built using the command "mvn clean install -DskipTests". Any specific testcase can be run using the 
following maven command:

mvn -DfailIfNoTests=false -Dtest=[class-name]#test test

for instance:

mvn -DfailIfNoTests=false -Dtest=ThreadUnsafeCounterTest#test test

The above command will run the test for the class ThreadUnsafeCounterTest. Also note that some of the tests would not
 terminate and run indefinitely since the service threads within the tests don't terminate, hence doing "mvn test" or 
 "mvn clean install" would hang the maven process.
