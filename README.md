# VFlow_2.0

----
> xonro's vflow project build by gradle.

工程包含的模块及其说明如下：
* bases：基础模块，包含工程公共功能部分，其他模块均依赖该模块。主要有工具类功能、缓存管理；
* client：工程的客户端程序，即前台部分，这部分内容将在Nginx中部署；
* console：控制台模块，系统管理功能程序，存储命名以`b_xr_console_`为前缀，视图则以`v_xr_console_`为前缀；
* dataview：数据可视化功能模块，存储命名以`b_xr_dataview_`为前缀，视图则以`v_xr_dataview_`为前缀；
* wechat：微信公众平台功能模块，存储命名以`b_xr_wechat_`为前缀，视图则以`v_xr_wechat_`为前缀；
* workflow：工作流功能模块，存储命名以`b_xr_workflow_`为前缀，视图则以`v_xr_workflow_`为前缀；
* wxpay：微信支付功能模块，存储命名以`b_xr_wxpay_`为前缀，视图则以`v_xr_wxpay_`为前缀；