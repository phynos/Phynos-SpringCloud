
source ./startup.sh

apps=(ChargerMonitor ChargerEureka ChargerConfig ChargerGateway ChargerAuth)

# 杀掉所有进程
echo '杀掉所有进程'
killApps $apps

# 启动进程
echo '开始启动进程'
startApps $apps