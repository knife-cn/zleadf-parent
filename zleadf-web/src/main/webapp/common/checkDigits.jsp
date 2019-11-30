<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
/**
* 数字检查
* @param selector 选择对象，一般为this
* @param canBeEmpty 是否可以为空
* @param min 最小值
* @param max 最大值
* @param precision 精度，目前只支持0-4之间的整数
* @param callback 回调方法
 */
function checkDigit(selector,canBeEmpty,min,max,precision,callback) {
  if(selector == null || (canBeEmpty != true && canBeEmpty != false)
    || typeof min != "number" || typeof max != "number" || typeof precision != "number") return;
  $selector = $(selector); //
  var val = $selector.val(); // 获取值转换为jquery对象
  if(isBlank(val) && canBeEmpty) { // 如果值为空，且允许为空，则设置为空
    return $selector.val("");
  }
  if(isBlank(val) && !canBeEmpty) { // 如果值为空，且不允许为空，则焦点不能离开
    return $selector.focus();
  }
  precision = Math.floor(precision);
  if(precision < 0) precision = 0;
  if(precision > 4) precision = 4;
  var precisionUnit = 1; // 最小单位精度
  if(precision == 1) precisionUnit = 0.1;
  if(precision == 2) precisionUnit = 0.01;
  if(precision == 3) precisionUnit = 0.001;
  if(precision == 4) precisionUnit = 0.0001;
  if(isNaN(val)) {  // 如果不是数字，则取最小值
    val = min;
  }
  val = Number(val);  // 转换为数字
  min = Number(min);  // 最小值
  if(val < min) val = min;// 如果可以包含最小值，则取最小值
  if(val > max) val = max;// 如果可以包含最大值，则取最大值
  $selector.val(val.toFixed(precision));
  //$selector.val(precision);
  if(callback) callback.call();
}

/** 设置数字输入框的title */
function setDigitTitle(selector,canBeEmpty,min,max,precision){
  $selector = $(selector);
  var title = $selector.attr("title");
  if(isBlank(title)) {
    title = "取值范围为：" + min + "至" + max;
    if(precision == 0) title += "，只能为整数";
    else title += "，保留" + Math.floor(precision) + "位小数";
    $selector.attr("title",title);
  }
}
</script>