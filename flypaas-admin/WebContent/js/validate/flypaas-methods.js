//自定义验证方法

/*
 * 手机号码验证
 */
jQuery.validator.addMethod("mobile_real", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9\._-]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]{2,4}|(13[0-9]|15[012356789]|18[02356789]|14[57])[0-9]{8}$/.test(value);
}, "请输入有效的手机号码");

jQuery.validator.addMethod("mobile", function(value, element) {
	return this.optional(element) || /^1[0-9]{10}$/.test(value);
}, "请输入有效的手机号码");


jQuery.validator.addMethod("version_index", function(value, element) {
	return this.optional(element) || /^[0-9]+\.[0-9]+\.[0-9]+$/.test(value);
}, "请输入正确的版本号 x.x.x 如 1.1.2");

/*
 * email验证
 */
jQuery.validator.addMethod("email2", function(value, element) {
	return this.optional(element) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
}, "请输入有效的电子邮件");

/*
 * 绑定必填：绑定的另一个元素不为空，则本元素必填 
 */
jQuery.validator.addMethod("bindRequired", function(value, element, param) {
	return $("#"+param).val()=="" || value!="";
}, "必须填写");
