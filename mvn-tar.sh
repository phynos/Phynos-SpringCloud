
apps=(CloudMonitor CloudEureka CloudConfig CloudGateway CloudAuth2 CloudProductSys CloudProductUser CloudConsumerUser)

rm -rf ./*.jar

cp phynos-monitor/target/apps[0] ./
cp phynos-eureka/target/apps[1] ./
cp phynos-config/target/apps[2] ./
cp phynos-gateway/target/apps[3] ./
cp phynos-auth/target/apps[4] ./


cp phynos-service-product/phynos-product-sys/target/apps[5] ./
cp phynos-service-product/phynos-product-user/target/apps[6] ./

cp phynos-service-consumer/phynos-consumer-user/target/apps[7] ./

tar -czvf phynos-prod.tar.gz *.jar