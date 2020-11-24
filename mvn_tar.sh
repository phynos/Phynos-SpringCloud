
dist=./dist
mkdir -p dist

echo start build...
mvn clean package -am -Dmaven.test.skip=true

echo clean files...
rm -rf ${dist}/*.jar
rm -rf ${dist}/*.gz

function buildBaseApps() {
    echo build base apps:
    apps=(charger-monitor charger-eureka charger-config charger-gateway charger-auth)
    for app in "${apps[@]}"
    do
      echo "build,app=${app}"
      cp ${app}/target/${app}.jar $dist
    done
}

function buildProduct() {
    echo build product apps:
    dir=charger-product
    apps=(charger-product-sys charger-product-user)
    for app in "${apps[@]}"
    do
      proj=$app
      echo "build,app=${app}"
      cp ${dir}/${proj}/target/${app}.jar $dist
    done
}

function buildConsumer() {
    echo build consumer apps:
    dir=charger-consumer
    apps=(charger-consumer-user)
    for app in "${apps[@]}"
    do
      proj=$app
      echo "build,app=${app}"
      cp ${dir}/${proj}/target/${app}.jar $dist
    done
}

buildBaseApps
buildProduct
buildConsumer

cd $dist
tag=`date '+%Y%m%d_%H%M%S'`
tar -czvf "charger${tag}.tar.gz" *.jar

cd ../

mvn clean