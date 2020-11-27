
source ./startup.sh

apps=(charger-monitor charger-eureka charger-config charger-gateway charger-atuh)

# 杀掉所有进程
echo '杀掉所有进程'
killApps $apps

# 启动进程
echo '开始启动进程'
startApps $apps