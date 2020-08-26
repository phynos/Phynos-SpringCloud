

PROGRAM='/home/lupc/apps'
apps=(CloudMonitor CloudEureka CloudConfig CloudGateway CloudAuth2 CloudProductSys CloudProductUser CloudConsumerUser)

for app in "${apps[@]}"
do
  PID=`ps -ef|grep -v grep | grep $app| awk '{print $2}'`
  if [ $PID -eq 0 ];then
    echo 'app: '$app',pid: is empty'
  else
    echo 'kill app: '$app',pid:'$PID
    kill -9 $PID
  fi

done


rm -rf *.jar
tar -xzvf phynos-prod.tar.gz -C $PROGRAM

cd $PROGRAM

for app in "${apps[@]}"
do
	echo 'run: '$app
	nohup java -jar $app.jar >/dev/null 2>&1 &
done
