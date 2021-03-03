Обновить версии всех библиотек в проекте и ее вывод:
- mvn versions:display-dependency-updates
  [INFO] The following dependencies in Dependencies have newer versions:
  [INFO]   org.seleniumhq.selenium:selenium-java ....... 3.141.59 -> 4.0.0-beta-1
  [INFO]   org.testng:testng ..................................... 7.1.0 -> 7.3.0

- mvn clean test
  [INFO] Results:
  [INFO]
  [INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
  [INFO]
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
  [INFO] Total time:  24.312 s
  
- clean test -Dtest=AuthorizationTest -f pom.xml