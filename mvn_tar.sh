
apps=(CloudMonitor CloudEureka CloudConfig CloudGateway CloudAuth2 CloudProductSys CloudProductUser CloudConsumerUser)

rm -rf ./*.jar

cp phynos-monitor/target/${apps[0]}.jar ./
cp phynos-cloud-eureka/target/${apps[1]}.jar ./
cp phynos-cloud-config/target/${apps[2]}.jar ./
cp phynos-cloud-gateway/target/${apps[3]}.jar ./
cp phynos-auth/target/${apps[4]}.jar ./


cp phynos-cloud-product/phynos-cloud-product-sys/target/${apps[5]}.jar ./
cp phynos-cloud-product/phynos-cloud-product-user/target/${apps[6]}.jar ./

cp phynos-cloud-consumer/phynos-cloud-consumer-user/target/${apps[7]}.jar ./

tar -czvf phynos-prod.tar.gz *.jar