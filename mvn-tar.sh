

rm ./*.jar

cp phynos-monitor/target/apps ./
cp phynos-eureka/target/apps ./
cp phynos-config/target/apps ./
cp phynos-gateway/target/apps ./
cp phynos-auth/target/apps ./


cp phynos-service-product/phynos-product-sys/target/apps ./
cp phynos-service-product/phynos-product-user/target/apps ./

cp phynos-service-consumer/phynos-consumer-user/target/apps ./

tar -czvf phynos-prod.tar.gz *.jar