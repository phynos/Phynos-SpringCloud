
apps=(ChargerMonitor ChargerEureka ChargerConfig ChargerGateway ChargerAuth2 ChargerSys ChargerUser ChargerConsumerUser)

rm -rf ./*.jar

cp charger-monitor/target/${apps[0]}.jar ./
cp charger-eureka/target/${apps[1]}.jar ./
cp charger-config/target/${apps[2]}.jar ./
cp charger-gateway/target/${apps[3]}.jar ./
cp charger-auth/target/${apps[4]}.jar ./


cp charger-product/charger-product-sys/target/${apps[5]}.jar ./
cp charger-product/charger-product-user/target/${apps[6]}.jar ./

cp charger-consumer/charger-consumer-user/target/${apps[7]}.jar ./

tar -czvf phynos-prod.tar.gz *.jar