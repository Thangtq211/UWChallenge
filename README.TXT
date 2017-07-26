========================================================================
Task
========================================================================
Write a class Network . The constructor should take a positive integer value indicating the
number of elements in the set. Passing in an invalid value should throw an exception . The
class should also provide two public methods, connect and query . The first method,
connect will take two integers indicating the elements to connect. This method should throw
exceptions as appropriate. The second method, query will also take two integers and should
also throw an exception as appropriate. It should return true if the elements are connected,
directly or indirectly, and false if the elements are not connected. The class can have as
many private or protected members as needed for a good implementation.
We have a set of elements: 1,2,3,4,5,6,7,8
We have a set of connections: 1-6,1-2, 6-2, 2-4, 5-8,
========================================================================
README.TXT
========================================================================
*** directory structures *****************************
* src			        Application/Library sources.
* src\main\resources    Application configuration and data for loading to the H2 database.
* target		        The target directory is used to store all output of the build with mvn command.
* log			        storing log files - for log4j.
* pom.xml		        for building maven project.
* README.TXT		    Project's readme
========================================================================
Technology
========================================================================
1. Java 8 (lambda, stream ).
2. Spring boot framework.
3. Spring persistence: JPA,H2 embedded database.
4. Log4j for logging.
5. Maven for building the project.
========================================================================
Testing
========================================================================

1. Testing

running the com.uw.challenge.NetworkImplTest class


