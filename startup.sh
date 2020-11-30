
function killApps() {
  apps=$@
  for app in "${apps[@]}"
  do
    PID=`ps -ef|grep -v grep | grep $app| awk '{print $2}'`
    if [ -z $PID ];then
      echo 'app: '$app',pid: is empty'
    else
      echo 'kill app: '$app',pid:'$PID
      kill -9 $PID
    fi
  done
}

function startApps() {
  if [ -z $dir ];then
    echo please set global var:dir
    return -1
  fi
  for app in "${apps[@]}"
  do
    echo 'run: '$app
    # 必须使用绝对路径启动jar，否则jps命令无法获取名称
    nohup java -jar $dir/$app.jar >/dev/null 2>&1 &
  done
}

function repackage() {
  if [ -z $dir ];then
    echo please set global var:dir
    return -1
  fi
  package=$2
  if [  -f $package ];then
    echo "package exist:$package"
  else
    echo "package not found:$package"
    exit 1
  fi
  rm -rf $dir
  mkdir -p $dir
  cd $dir
  tar -xzvf ../$package -C $dir
}

function backupApps() {

}

# dir='/home/lupc/apps'
# backup='/home/lupc/backup'
