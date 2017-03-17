import Vue from 'vue';
import VueResource from 'vue-resource';
import App from './App';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-default/index.css';    // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题

global.contextPath = '/Hammer-rest';//服务器端的contextPath
// 全局错误提示方法
Vue.prototype.globalFail = function(response){
    if(response.ok){
        this.$message(
            {
                showClose: true,
                message:'status : ' + response.body.operationState +' reason : ' + response.body.data.reason,
                type: 'error'
            }
        );
    }else {
        this.$message(
            {
                showClose: true,
                message:'response with http status : ' + response.status,
                type: 'error'
            }
        );
    }
}
// get wapper
Vue.prototype.get = function (url,success){//全局get请求函数
    this.$http.get(url).then(response => {
        if(response.body && response.body.operationState == 'SUCCESS'){
            success(response.body);
        }else {//200状态码但是业务响应是错误的
            this.globalFail(response);
        }

    }, this.globalFail);
}
// post body wapper
Vue.prototype.postBody = function (url,body,success){//全局post请求函数
    this.$http.post(url, body,{
        method:'POST',
        timeout:5000
    }).then(response => {
        if(response.body && response.body.operationState == 'SUCCESS'){
            success(response.body);
        }else {//200状态码但是业务响应是错误的
            this.globalFail(response);
        }
    }, this.globalFail);
}
//post form
Vue.prototype.postForm = function (url,params,success){//全局post请求函数
    this.$http.post(url, undefined,{
        method:'POST',
        params:params,
        timeout:5000
    }).then(response => {
        if(response.body && response.body.operationState == 'SUCCESS'){
            success(response.body);
        }else {//200状态码但是业务响应是错误的
            this.globalFail(response);
        }
    }, this.globalFail);
}

Vue.use(ElementUI);
Vue.use(VueResource);
// Vue.http.options.emulateJSON = true;
new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
