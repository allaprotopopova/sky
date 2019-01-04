mvn -B -s settings.xml -DskipTests=true clean package
java -DDATABASE_URL="postgres://ztkchfmkudwrmx:9091e0c28fdc1bc89776a15800e4aa35ab5cc12dec862de2f836211d6c369073@ec2-174-129-18-247.compute-1.amazonaws.com:5432/d7eh52uaenodl4" -jar target/dependency/webapp-runner.jar target/*.war
