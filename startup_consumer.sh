
source ./startup.sh

dir='/home/lupc/apps-biz'

repackage $dir charger-biz.tar.gz

apps=(charger-product-sys charger-product-user)

# 杀掉所有进程
echo '杀掉所有进程'
killApps $apps

# 启动进程
echo '开始启动进程'
startApps $apps