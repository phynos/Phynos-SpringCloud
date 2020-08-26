

rm ./*.jar

copy phynos-monitor/target/apps ./
copy phynos-eureka/target/apps ./
copy phynos-config/target/apps ./
copy phynos-gateway/target/apps ./
copy phynos-auth/target/apps ./


copy phynos-service-product/phynos-product-sys/target/apps ./
copy phynos-service-product/phynos-product-user/target/apps ./

copy phynos-service-consumer/phynos-consumer-user/target/apps ./

tar -czvf phynos-prod.tar.gz *.jar