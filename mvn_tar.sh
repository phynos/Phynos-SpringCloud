
dist=./dist
mkdir -p dist

echo start build...
mvn clean package -am -Dmaven.test.skip=true

echo clean files...
rm -rf ${dist}/*.jar
rm -rf ${dist}/*.gz

apps=(ChargerMonitor ChargerEureka ChargerConfig ChargerGateway ChargerAuth2 ChargerSys ChargerUser ChargerConsumerUser)

cp charger-monitor/target/${apps[0]}.jar $dist
cp charger-eureka/target/${apps[1]}.jar $dist
cp charger-config/target/${apps[2]}.jar $dist
cp charger-gateway/target/${apps[3]}.jar $dist
cp charger-auth/target/${apps[4]}.jar $dist


cp charger-product/charger-product-sys/target/${apps[5]}.jar $dist
cp charger-product/charger-product-user/target/${apps[6]}.jar $dist

cp charger-consumer/charger-consumer-user/target/${apps[7]}.jar $dist


cd $dist
tar -czvf charger.tar.gz *.jar

mvn clean