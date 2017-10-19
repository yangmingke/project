//该函数接收3个参数：cookie名称，cookie值，以及在多少小时后过期。这里约定expireHours为0时不设定过期时间，即当浏览器关闭时cookie自动消失
function addcookie(name,value,expireHours){
      var cookieString=name+"="+escape(value)+";domain=192.168.0.109;path=/";
      //判断是否设置过期时间
      if(expireHours>0){
             var date=new Date();
             date.setTime(date.getTime()+expireHours*3600*1000);
             cookieString=cookieString+";expires="+date.toGMTString();
      }
      document.cookie=cookieString;
}
//该函数返回名称为name的cookie值，如果不存在则返回空
function getcookie(name){
    var strcookie=document.cookie;
    var arrcookie=strcookie.split("; ");
    for(var i=0;i<arrcookie.length;i++){
          var arr=arrcookie[i].split("=");
          if(arr[0]==name){
        	  return arr[1];
          }
    }
    return null;
}
//该函数可以删除指定名称的cookie
function deletecookie(name){
    var date=new Date();
    date.setTime(date.getTime()-10000);
    document.cookie=name+"=null;path=/; expire="+date.toGMTString();
}